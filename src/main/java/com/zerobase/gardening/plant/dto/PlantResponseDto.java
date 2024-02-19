package com.zerobase.gardening.plant.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlantResponseDto {

    private long plantId;

    private String imgKey;

    private String imgUrl;

    private String name;

    private String species;

    private LocalDate plantingDate;

    private int wateringCycle;

    private String description;

}
