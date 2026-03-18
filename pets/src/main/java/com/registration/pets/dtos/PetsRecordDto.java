package com.registration.pets.dtos;

import com.registration.pets.models.PetsModel.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PetsRecordDto(@NotBlank String name, @NotBlank String lastName, @NotBlank String city,
                            @NotBlank String adress,@NotNull Sex sex, @NotNull Double weight, @NotNull Double age, @NotBlank String type) {
}
