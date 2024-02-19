package com.zerobase.gardening.plant.controller;

import com.zerobase.gardening.plant.dto.PlantEditDto;
import com.zerobase.gardening.plant.dto.PlantRegistrationDto;
import com.zerobase.gardening.plant.dto.PlantResponseDto;
import com.zerobase.gardening.plant.service.AwsS3Service;
import com.zerobase.gardening.plant.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlantController {
    private final PlantService plantService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AwsS3Service awsS3Service;

    @PostMapping("/plant/registration")
    public ResponseEntity<?> registration(
            @RequestPart(value = "dto") PlantRegistrationDto dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        if (!file.isEmpty()) {
            String key = awsS3Service.uploadFile(file);
            dto.setImgKey(key);
        }

        boolean result = plantService.registration(dto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/plant/list")
    public ResponseEntity<?> list(@RequestParam long userId) {
        List<PlantResponseDto> list = plantService.list(userId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/plant/detail")
    public ResponseEntity<?> detail(@RequestParam long plantId) {
        PlantResponseDto detail = plantService.detail(plantId);
        detail.setImgUrl(awsS3Service.getUrl(detail.getImgKey()));
        return ResponseEntity.ok().body(detail);
    }

    @DeleteMapping("/plant/delete")
    public ResponseEntity<?> delete(@RequestParam long plantId) {
        String key = plantService.delete(plantId);
        awsS3Service.delete(key);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/plant/edit")
    public ResponseEntity<?> edit(@RequestPart(value = "editDto") PlantEditDto editDto,
                                  @RequestPart(value = "file", required = false) MultipartFile file) {
        if (file != null) {
            String key = awsS3Service.uploadFile(file);
            editDto.setImgKey(key);
            String previousKey = plantService.edit(editDto);
            awsS3Service.delete(previousKey);
        }

        plantService.edit(editDto);
        return ResponseEntity.ok().body(true);
    }
}
