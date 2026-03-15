package com.registration.pets.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="TB_PETS")
public class PetsModel extends RepresentationModel<PetsModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPets;
    private String name;
    private String lastName;
    private enum Sex {
        MACHO,FEMEA
    };
    private PetsAdress petsAdress;
    private double weight;
    private double age;
    private String city;

    public UUID getIdPets() {
        return idPets;
    }

    public void setIdPets(UUID idPets) {
        this.idPets = idPets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PetsAdress getPetsAdress() {
        return petsAdress;
    }

    public void setPetsAdress(PetsAdress petsAdress) {
        this.petsAdress = petsAdress;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}