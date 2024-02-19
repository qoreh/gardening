package com.zerobase.gardening.plant.service;

import com.zerobase.gardening.plant.dto.PlantEditDto;
import com.zerobase.gardening.plant.dto.PlantRegistrationDto;
import com.zerobase.gardening.plant.dto.PlantResponseDto;

import java.util.List;

public interface PlantService {

    boolean registration(PlantRegistrationDto registrationDto);

    List<PlantResponseDto> list(long userId);

    PlantResponseDto detail(long plantId);

    String delete(long plantId);

    String edit(PlantEditDto editDto);
}
