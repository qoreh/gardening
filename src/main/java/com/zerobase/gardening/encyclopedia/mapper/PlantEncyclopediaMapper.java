package com.zerobase.gardening.encyclopedia.mapper;

import com.zerobase.gardening.encyclopedia.dto.DetailResponse;
import com.zerobase.gardening.encyclopedia.entity.PlantEncyclopedia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlantEncyclopediaMapper {

    PlantEncyclopediaMapper INSTANCE = Mappers.getMapper(PlantEncyclopediaMapper.class);

    PlantEncyclopedia toPlantEncyclopedia(DetailResponse.DetailItem plantDetailDto);

    ArrayList<PlantEncyclopedia> toPlantEncyclopediaList(List<DetailResponse.DetailItem> plantDetailDtoList);
}
