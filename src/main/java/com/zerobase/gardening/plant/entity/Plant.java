package com.zerobase.gardening.plant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    @Column(columnDefinition = "TEXT")
    private String imgKey;

    private String name;

    private String species;

    private LocalDate plantingDate;

    private int wateringCycle;

    @Column(columnDefinition = "TEXT")
    private String description;

}
