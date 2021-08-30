package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.id = :petId")
    List<Schedule> findAllByPetId(Long petId);

    @Query("SELECT s FROM Schedule s JOIN s.employees e WHERE e.id = :employeeId")
    List<Schedule> findAllByEmployeeId(Long employeeId);

}
