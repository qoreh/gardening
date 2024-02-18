package com.zerobase.gardening.encyclopedia.service;

import com.zerobase.gardening.encyclopedia.dto.DetailResponse;

import java.util.List;

public interface ApiService {
    boolean save(List<DetailResponse.DetailItem> dtoList);

}
