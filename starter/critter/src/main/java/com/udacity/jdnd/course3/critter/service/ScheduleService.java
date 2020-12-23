package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    public Schedule createSchedule(Schedule schedule, List<Long> petIds, List<Long> employeeIds)
    {
        List<Pet> pets = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        for (Long id : petIds)
        {
            Pet pet = petService.findPetById(id);
            pets.add(pet);
        }
        for (Long id : employeeIds)
        {
            Employee employee = employeeService.findEmployeeById(id);
            employees.add(employee);
        }
        schedule.setPets(pets);
        schedule.setEmployees(employees);
        return scheduleRepository.save(schedule);
    }

}
