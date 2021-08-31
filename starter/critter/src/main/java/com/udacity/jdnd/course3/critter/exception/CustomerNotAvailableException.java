package com.udacity.jdnd.course3.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Customer not found")
public class CustomerNotAvailableException extends RuntimeException {
    public CustomerNotAvailableException() {
        super("The requested customer is not available");
    }

    public CustomerNotAvailableException(Long id) {
        super("The requested customer is not available. Id: " + id);
    }
}
