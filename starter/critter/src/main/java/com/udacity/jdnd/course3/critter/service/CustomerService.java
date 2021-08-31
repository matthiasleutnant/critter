package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.CustomerNotAvailableException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long Id) {
        Optional<Customer> customer = customerRepository.findById(Id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotAvailableException(Id);
        }
    }

    public void addPetToCustomer(Pet pet, Customer customer) {
        List<Pet> petList = customer.getPets();
        if (!petList.contains(pet)) {
            petList.add(pet);
        }
        pet.setCustomer(customer);
    }

    public void addPetToCustomerByCustomerId(Pet pet, Long customerId) {
        Customer customer = findCustomerById(customerId);
        addPetToCustomer(pet,customer);
    }
}
