package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public static CustomerDTO convertEntityToCustomerDTO(Customer customer)
    {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Pet> pets = customer.getPets();
        if(pets != null)
        {
            List<Long> petIds = new ArrayList<>();
            for (Pet pet : pets)
            {
                petIds.add(pet.getId());
            }
            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    public static Customer convertCustomerDTOtoEntity(CustomerDTO customerDTO)
    {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public static List<CustomerDTO> convertEntityListToCustomerDTOList(List<Customer> customerList)
    {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList)
        {
            CustomerDTO customerDTO = CustomerDTO.convertEntityToCustomerDTO(customer);
            List<Pet> pets = customer.getPets();
            if(pets != null)
            {
                List<Long> petIds = new ArrayList<>();
                for (Pet pet : pets)
                {
                    petIds.add(pet.getId());
                }
                customerDTO.setPetIds(petIds);
            }
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
