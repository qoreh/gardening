package com.zerobase.gardening.plant.mapper;

import com.zerobase.gardening.plant.dto.PlantEditDto;
import com.zerobase.gardening.plant.dto.PlantRegistrationDto;
import com.zerobase.gardening.plant.dto.PlantResponseDto;
import com.zerobase.gardening.plant.entity.Plant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlantMapper {

    PlantMapper INSTANCE = Mappers.getMapper(PlantMapper.class);

    Plant toPlant(PlantRegistrationDto registrationDto);

    void editToPlant(PlantEditDto editDto, @MappingTarget Plant plant);

    @Mapping(source = "id", target = "plantId")
    PlantResponseDto toResponseDto(Plant plant);

    List<PlantResponseDto> toResponseDtoList(List<Plant> plantList);

}
