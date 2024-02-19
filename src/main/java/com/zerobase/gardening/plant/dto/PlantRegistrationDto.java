package com.zerobase.gardening.plant.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlantRegistrationDto {

    private long userId;

    private String imgKey;

    private String name;

    private String species;

    private long speciesId;

    private LocalDate plantingDate;

    private int wateringCycle;

    private String description;
}
