package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
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
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = ScheduleDTO.convertScheduleDTOtoEntity(scheduleDTO);
        schedule = scheduleService.createSchedule(schedule, scheduleDTO.getPetIds(), scheduleDTO.getEmployeeIds());
        return ScheduleDTO.convertEntityToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules()
    {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ScheduleDTO.convertEntityListToScheduleDTOList(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId)
    {
        List<Schedule> schedules =  scheduleService.getScheduleForPet(petId);
        return ScheduleDTO.convertEntityListToScheduleDTOList(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules =  scheduleService.getScheduleForEmployee(employeeId);
        return ScheduleDTO.convertEntityListToScheduleDTOList(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules =  scheduleService.getScheduleForCustomer(customerId);
        return ScheduleDTO.convertEntityListToScheduleDTOList(schedules);
    }
}
