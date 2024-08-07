package com.example.employee_management.exception;

public class EmployeeStorageIsFullException  extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("Превышен лимит количества сотрудников в фирме");
    }
}
