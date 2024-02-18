package com.zerobase.gardening.encyclopedia.service;

import com.zerobase.gardening.encyclopedia.dto.DetailResponse;
import com.zerobase.gardening.encyclopedia.entity.PlantEncyclopedia;
import com.zerobase.gardening.encyclopedia.mapper.PlantEncyclopediaMapper;
import com.zerobase.gardening.encyclopedia.repository.PlantEncyclopediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final PlantEncyclopediaMapper mapper = PlantEncyclopediaMapper.INSTANCE;
    private final PlantEncyclopediaRepository plantEncyclopediaRepository;

    @Override
    public boolean save(List<DetailResponse.DetailItem> dtoList) {
        ArrayList<PlantEncyclopedia> plantEncyclopediaList = mapper.toPlantEncyclopediaList(dtoList);
        return !ObjectUtils.isEmpty(plantEncyclopediaRepository.saveAll(plantEncyclopediaList));
    }
}
