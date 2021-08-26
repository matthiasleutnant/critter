package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.exception.EmplyeeNotAvailableException;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id){
        return employeeRepository.getOne(id);
    }

    public void updateAvailabilty(Long id, Set<DayOfWeek> daysAvailable){
        Optional<Employee> emplyee = employeeRepository.findById(id);
        if(emplyee.isPresent()){
            emplyee.get().setDaysAvailable(daysAvailable);
            employeeRepository.save(emplyee.get());
        }
        else{
            throw new EmplyeeNotAvailableException();
        }
    }
}
