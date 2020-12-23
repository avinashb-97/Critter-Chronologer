package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO)
    {
        Pet pet = PetDTO.convertPetDTOtoEntity(petDTO);
        pet = petService.savePet(pet, petDTO.getOwnerId());
        return PetDTO.convertEntitytoPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId)
    {
        Pet pet = petService.findPetById(petId);
        return PetDTO.convertEntitytoPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets()
    {
        List<Pet> pets = petService.getAllPets();
        return PetDTO.convertEntityListToPetDTOList(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.getPetsByOwnerId(ownerId);
        return PetDTO.convertEntityListToPetDTOList(petList);
    }

}
