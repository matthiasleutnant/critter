package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PetService petService;
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertScheduleToScheduleDto(scheduleService.save(convertScheduleDtoToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> result = new ArrayList<>();
        scheduleService.findAll().forEach(
                schedule -> {
                    result.add(convertScheduleToScheduleDto(schedule));
                }
        );
        return result;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> result = new ArrayList<>();
        scheduleService.findAllByPetId(petId).forEach(
                schedule -> {
                    result.add(convertScheduleToScheduleDto(schedule));
                }
        );
        return result;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> result = new ArrayList<>();
        scheduleService.findAllByEmployeeId(employeeId).forEach(
                schedule -> {
                    result.add(convertScheduleToScheduleDto(schedule));
                }
        );
        return result;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }

    public Schedule convertScheduleDtoToSchedule(ScheduleDTO scheduleDto) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDto, schedule);
        List<Employee> employeeList = new ArrayList<>();
        for (Long id : scheduleDto.getEmployeeIds()) {
            employeeList.add(employeeService.getEmployee(id));
        }
        schedule.setEmployees(employeeList);
        List<Pet> petList = new ArrayList<>();
        for (Long id : scheduleDto.getPetIds()) {
            petList.add(petService.getPet(id));
        }
        schedule.setPets(petList);
        return schedule;
    }

    public ScheduleDTO convertScheduleToScheduleDto(Schedule schedule) {
        ScheduleDTO scheduleDto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDto);
        List<Long> employeeList = new ArrayList<>();
        for (Employee e : schedule.getEmployees()) {
            employeeList.add(e.getId());
        }
        scheduleDto.setEmployeeIds(employeeList);
        List<Long> petList = new ArrayList<>();
        for (Pet p : schedule.getPets()) {
            petList.add(p.getId());
        }
        scheduleDto.setPetIds(petList);
        return scheduleDto;
    }
}
