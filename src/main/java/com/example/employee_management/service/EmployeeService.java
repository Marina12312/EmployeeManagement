package com.example.employee_management.service;

import com.example.employee_management.Employee;
import com.example.employee_management.exception.EmployeeAlreadyAddedException;
import com.example.employee_management.exception.EmployeeNotFoundException;
import com.example.employee_management.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private static final int MAX_EMPLOYEES = 100;

    public void addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException();
        }

        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        employees.add(newEmployee);
    }

    public void removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);

        if (employees.remove(employeeToRemove)){
            throw new EmployeeNotFoundException();
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        return employees.stream()
                .filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
}