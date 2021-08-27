package com.udacity.jdnd.course3.critter.exception;

public class PetNotAvailableExeption extends RuntimeException {
    public PetNotAvailableExeption() {
        super("The requested pet is not available");
    }

    public PetNotAvailableExeption(long petId) {
        super("The requested pet is not available.PetId :" + petId);
    }
}
