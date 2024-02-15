package com.zerobase.gardening.encyclopedia.service;

import com.zerobase.gardening.encyclopedia.dto.PlantDetailDto;

import java.util.ArrayList;

public interface ApiService {
    boolean save(ArrayList<PlantDetailDto> dtoList);

}
