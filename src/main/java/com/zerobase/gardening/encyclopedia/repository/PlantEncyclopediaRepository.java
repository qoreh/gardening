package com.zerobase.gardening.encyclopedia.repository;

import com.zerobase.gardening.encyclopedia.entity.PlantEncyclopedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantEncyclopediaRepository extends JpaRepository<PlantEncyclopedia, Long> {
}
