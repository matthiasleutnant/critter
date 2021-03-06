package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;
    @Autowired
    CustomerService customerService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertPetToPetDTO(petService.save(convertPetDTOToPet(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<PetDTO> result = new ArrayList<>();
        petService.getAll().forEach(
                schedule -> {
                    result.add(convertPetToPetDTO(schedule));
                }
        );
        return result;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> result = new ArrayList<>();
        Customer owner = customerService.findCustomerById(ownerId);
        owner.getPets().stream().forEach(pet -> {
            result.add(convertPetToPetDTO(pet));
        });
        return result;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        customerService.addPetToCustomerByCustomerId(pet, petDTO.getOwnerId());
        return pet;
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }
}
