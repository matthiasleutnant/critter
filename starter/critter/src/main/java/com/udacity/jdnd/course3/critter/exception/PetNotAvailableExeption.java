package com.udacity.jdnd.course3.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet not found")
public class PetNotAvailableExeption extends RuntimeException {
    public PetNotAvailableExeption() {
        super("The requested pet is not available");
    }

    public PetNotAvailableExeption(long petId) {
        super("The requested pet is not available.PetId :" + petId);
    }
}
