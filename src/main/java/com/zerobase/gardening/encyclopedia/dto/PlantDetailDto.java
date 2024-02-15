package com.zerobase.gardening.encyclopedia.dto;

import com.zerobase.gardening.encyclopedia.type.*;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class PlantDetailDto {

    private String scientificName;

    private String englishName;

    private String tradeName;

    private String familyName;

    private String nativeHabitat;

    private String manageLevel;

    private String manageDemandLevel;

    private String place;

    private String bloomingSeason;

    private String flowerColor;

    private String leafShape;

    private String leafColor;

    private String propagation;

    private String lightDemandLevel;

    private String diseaseAndPest;

    private String growthVelocity;

    private String growthHeight;

    private String growthWidth;

    private String growthTemperature;

    private String winterLowestTemperature;

    private String humidity;

    private String fertilizer;

    private String soil;

    private String springWateringCycle;

    private String summerWateringCycle;

    private String fallWateringCycle;

    private String winterWateringCycle;

    private String etc;
}
