package com.zerobase.gardening.plant.service;

import com.zerobase.gardening.encyclopedia.repository.PlantEncyclopediaRepository;
import com.zerobase.gardening.exception.CustomException;
import com.zerobase.gardening.plant.dto.PlantEditDto;
import com.zerobase.gardening.plant.dto.PlantRegistrationDto;
import com.zerobase.gardening.plant.dto.PlantResponseDto;
import com.zerobase.gardening.plant.entity.Plant;
import com.zerobase.gardening.plant.mapper.PlantMapper;
import com.zerobase.gardening.plant.repository.PlantRepository;
import com.zerobase.gardening.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.zerobase.gardening.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {
    private final PlantRepository plantRepository;
    private final PlantEncyclopediaRepository encyclopediaRepository;
    private final UserRepository userRepository;
    private final PlantMapper plantMapper = PlantMapper.INSTANCE;

    @Override
    public boolean registration(PlantRegistrationDto registrationDto) {
        // user 확인
        userRepository.findById(registrationDto.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 식물도감 확인
        encyclopediaRepository.findById(registrationDto.getSpeciesId())
                .orElseThrow(() -> new CustomException(UNREGISTERED_IN_PLANT_ENCYCLOPEDIA));

        // dto -> entity
        Plant savedPlant = plantRepository
                .save(plantMapper.toPlant(registrationDto));
        return !ObjectUtils.isEmpty(savedPlant);
    }

    @Override
    public List<PlantResponseDto> list(long userId) {
        List<Plant> plantList = plantRepository.findAllByUserId(userId);
        return plantMapper.toResponseDtoList(plantList);
    }

    @Override
    public PlantResponseDto detail(long plantId) {
        Plant plant = plantRepository.findById(plantId).orElseThrow(
                () -> new CustomException(PLANT_NOT_FOUND));
        return plantMapper.toResponseDto(plant);
    }

    @Override
    public String delete(long plantId) {
        Plant plant = plantRepository.findById(plantId).orElseThrow(
                () -> new CustomException(PLANT_NOT_FOUND));
        plantRepository.delete(plant);
        return plant.getImgKey();
    }

    @Override
    public String edit(PlantEditDto editDto) {
        Plant plant = plantRepository.findById(editDto.getId())
                .orElseThrow(() -> new CustomException(PLANT_NOT_FOUND));
        String previousKey = plant.getImgKey();
        if (!StringUtils.hasText(editDto.getImgKey())) {
            editDto.setImgKey(previousKey);
        }

        plantMapper.editToPlant(editDto, plant);
        plantRepository.save(plant);
        return previousKey;
    }

}
