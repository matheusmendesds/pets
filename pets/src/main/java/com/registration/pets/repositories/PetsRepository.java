package com.registration.pets.repositories;

import com.registration.pets.models.PetsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetsRepository extends JpaRepository<PetsModel, UUID> {

}
