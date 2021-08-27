package com.udacity.jdnd.course3.critter.exception;

import com.udacity.jdnd.course3.critter.entity.Customer;

public class CustomerNotAvailableException extends RuntimeException {
    public CustomerNotAvailableException() {
        super("The requested customer is not available");
    }

    public CustomerNotAvailableException(Long id) {
        super("The requested customer is not available. Id: " + id);
    }
}
