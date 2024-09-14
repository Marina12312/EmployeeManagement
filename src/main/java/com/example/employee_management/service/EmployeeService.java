package com.example.employee_management.service;

import com.example.employee_management.Employee;
import com.example.employee_management.exception.EmployeeAlreadyAddedException;
import com.example.employee_management.exception.EmployeeNotFoundException;
import com.example.employee_management.exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private static final int MAX_EMPLOYEES = 100;

    public Employee addEmployee(String firstName, String lastName) {
        // Проверка входных данных
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Имя и фамилия не должны быть пустыми.");
        }


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
        // Проверка входных данных
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Имя и фамилия не должны быть пустыми.");
        }


        Employee employeeToRemove = new Employee(firstName, lastName);

        if (!employees.remove(employeeToRemove)) {
            throw new EmployeeNotFoundException();
        }
        return employeeToRemove;
    }

    public Employee findEmployee(String firstName, String lastName) {
        // Проверка входных данных
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
            throw new IllegalArgumentException("Имя и фамилия не должны быть пустыми.");
        }


        return employees.stream()
                .filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);


    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    }