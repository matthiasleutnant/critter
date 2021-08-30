package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotAvailableException;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotAvailableException(id);
        }
    }

    public void updateAvailabilty(Long id, Set<DayOfWeek> daysAvailable) {
        Optional<Employee> emplyee = employeeRepository.findById(id);
        if (emplyee.isPresent()) {
            emplyee.get().setDaysAvailable(daysAvailable);
            employeeRepository.save(emplyee.get());
        } else {
            throw new EmployeeNotAvailableException(id);
        }
    }

    public List<Employee> findBySkillAndDays(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        List<Employee> employees = employeeRepository.findDistinctBySkillsInAndDaysAvailable(skills, dayOfWeek);

        employees=employees.stream().filter(employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toList());

        return employees;
    }
}
