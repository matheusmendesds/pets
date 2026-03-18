package com.registration.pets.controllers;

import com.registration.pets.dtos.PetsRecordDto;
import com.registration.pets.models.PetsModel;
import com.registration.pets.repositories.PetsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PetsController {
    @Autowired
    PetsRepository petsRepository;

    @PostMapping("/pets")
    public ResponseEntity<PetsModel> savePet(@RequestBody @Valid PetsRecordDto petsRecordDto) {
        var petsModel = new PetsModel();
        BeanUtils.copyProperties(petsRecordDto, petsModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(petsRepository.save(petsModel));
    }

    @GetMapping("/pets")
    public ResponseEntity<List<PetsModel>> getAllPets() {
        List<PetsModel> petsList = petsRepository.findAll();
        if (!petsList.isEmpty()) {
            for (PetsModel pet : petsList) {
                UUID id = pet.getIdPets();
                pet.add(linkTo(methodOn(PetsController.class).getOnePet(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(petsList);

    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Object> getOnePet(@PathVariable(value = "id")UUID id ) {
        Optional<PetsModel> petO = petsRepository.findById(id);
        if (petO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found.");
        }
        petO.get().add(linkTo(methodOn(PetsController.class).getAllPets()).withRel("Pet Found"));
        return ResponseEntity.status(HttpStatus.OK).body(petO.get());
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Object> updatePet(@PathVariable(value = "id")UUID id ,@RequestBody @Valid PetsRecordDto petsRecordDto) {
        Optional<PetsModel> petO = petsRepository.findById(id);
        if (petO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var petModel = petO.get();
        BeanUtils.copyProperties(petsRecordDto, petModel);
        return ResponseEntity.status(HttpStatus.OK).body(petsRepository.save(petModel));
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Object> deletePet(@PathVariable(value = "id")UUID id) {
        Optional<PetsModel> petO = petsRepository.findById(id);
        if (petO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        petsRepository.delete(petO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pet deleted successfully");
    }
}
