package com.udacity.jdnd.course3.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee not found")
public class EmployeeNotAvailableException extends RuntimeException {
    public EmployeeNotAvailableException() {
        super("The requested employee is not available");
    }

    public EmployeeNotAvailableException(Long id) {
        super("The requested employee is not available. Id: " + id);
    }
}
