package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.exception.PetNotAvailableExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;
    public Pet save(Pet pet){
        return petRepository.save(pet);
    }

    public Pet getPet(long id){
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()){
            return pet.get();
        }
        else {
            throw new PetNotAvailableExeption(id);
        }
    }
}