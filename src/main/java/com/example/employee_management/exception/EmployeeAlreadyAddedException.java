package com.example.employee_management.exception;

public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException() {
        super("Сотрудник уже добавлен");
    }
}

