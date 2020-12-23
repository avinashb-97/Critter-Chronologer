package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;

    public Pet savePet(Pet pet, Long customerId)
    {
        Customer customer = customerService.addPet(pet, customerId);
        pet.setCustomer(customer);
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets()
    {
        return petRepository.findAll();
    }

    public Pet findPetById(Long id)
    {
        return petRepository.findById(id)
                .orElseThrow(()->new PetNotFoundException("Pet Not found"));
    }

    public List<Pet> getPetsByOwnerId(Long ownerId)
    {
        return petRepository.findPetsByCustomerId(ownerId);
    }

}
