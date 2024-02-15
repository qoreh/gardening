package com.zerobase.gardening.encyclopedia.entity;

import com.zerobase.gardening.encyclopedia.type.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlantEncyclopedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @Column(columnDefinition = "TEXT")
    private String etc;


}
