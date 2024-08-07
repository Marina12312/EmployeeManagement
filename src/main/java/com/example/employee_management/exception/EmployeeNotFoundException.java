package com.example.employee_management.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
            super("Сотрудник не найден");
        }
}
