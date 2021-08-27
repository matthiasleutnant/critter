package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.EmployeeNotAvailableException;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
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
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else {
            throw new EmployeeNotAvailableException(id);
        }
    }

    public void updateAvailabilty(Long id, Set<DayOfWeek> daysAvailable){
        Optional<Employee> emplyee = employeeRepository.findById(id);
        if(emplyee.isPresent()){
            emplyee.get().setDaysAvailable(daysAvailable);
            employeeRepository.save(emplyee.get());
        }
        else{
            throw new EmployeeNotAvailableException(id);
        }
    }
}
