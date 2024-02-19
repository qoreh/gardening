package com.zerobase.gardening.plant.repository;

import com.zerobase.gardening.plant.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    List<Plant> findAllByUserId(long userId);

    Optional<Plant> findById(long id);
}
