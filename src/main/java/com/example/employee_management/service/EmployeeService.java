package com.example.employee_management.service;

import com.example.employee_management.Employee;
import com.example.employee_management.exception.EmployeeAlreadyAddedException;
import com.example.employee_management.exception.EmployeeNotFoundException;
import com.example.employee_management.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private static final int MAX_EMPLOYEES = 100;

    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException();
        }

        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);

        if (!employees.remove(employeeToRemove)){
            throw new EmployeeNotFoundException();
        }
        return employeeToRemove;
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