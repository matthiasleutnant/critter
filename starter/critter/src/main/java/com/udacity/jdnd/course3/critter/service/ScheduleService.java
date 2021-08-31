package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    PetService petService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllByPetId(Long petId) {
        return scheduleRepository.findAllByPets(petService.getPet(petId));
    }

    public List<Schedule> findAllByEmployeeId(Long employeeId) {
        return scheduleRepository.findAllByEmployees(employeeService.getEmployee(employeeId));
    }

    public List<Schedule> findByCustomerId(Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return scheduleRepository.findAllByPetsIn(customer.getPets());
    }
}
