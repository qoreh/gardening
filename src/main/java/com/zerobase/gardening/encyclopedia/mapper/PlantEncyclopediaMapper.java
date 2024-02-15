package com.zerobase.gardening.encyclopedia.mapper;

import com.zerobase.gardening.encyclopedia.dto.PlantDetailDto;
import com.zerobase.gardening.encyclopedia.entity.PlantEncyclopedia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface PlantEncyclopediaMapper {

    PlantEncyclopediaMapper INSTANCE = Mappers.getMapper(PlantEncyclopediaMapper.class);
    PlantEncyclopedia toPlantEncyclopedia(PlantDetailDto plantDetailDto);
    ArrayList<PlantEncyclopedia> toPlantEncyclopediaList(ArrayList<PlantDetailDto> plantDetailDtoList);
}
