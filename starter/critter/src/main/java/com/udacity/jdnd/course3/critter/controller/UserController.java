package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO)
    {
        Customer customer = customerService.saveCustomer(CustomerDTO.convertCustomerDTOtoEntity(customerDTO));
        return CustomerDTO.convertEntityToCustomerDTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customerList = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList)
        {
            customerDTOList.add(CustomerDTO.convertEntityToCustomerDTO(customer));
        }
        return  customerDTOList;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId)
    {
        Customer customer = customerService.getCustomerByPetId(petId);
        return CustomerDTO.convertEntityToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        Employee employee = EmployeeDTO.convertEmployeeDTOtoEntity(employeeDTO);
        employee = employeeService.saveEmployee(employee);
        return EmployeeDTO.convertEntityToEmployeeDTO(employee);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId)
    {
        Employee employee = employeeService.findEmployeeById(employeeId);
        return EmployeeDTO.convertEntityToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        Set<EmployeeSkill> skillsNeeded= employeeDTO.getSkills();
        Set<DayOfWeek> dayOfWeeks = new HashSet<>();
        dayOfWeeks.add(employeeDTO.getDate().getDayOfWeek());
        List<Employee> employees = employeeService.findEmployeeForService(skillsNeeded, dayOfWeeks);
        return EmployeeDTO.convertEnitityListToEmployeeDTOList(employees);
    }

}
