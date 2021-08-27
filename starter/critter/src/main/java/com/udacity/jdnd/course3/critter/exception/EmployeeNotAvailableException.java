package com.udacity.jdnd.course3.critter.exception;

public class EmployeeNotAvailableException extends RuntimeException {
    public EmployeeNotAvailableException() {
        super("The requested employee is not available");
    }

    public EmployeeNotAvailableException(Long id) {
        super("The requested employee is not available. Id: " + id);
    }
}
