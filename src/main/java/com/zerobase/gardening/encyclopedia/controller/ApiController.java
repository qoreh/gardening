package com.zerobase.gardening.encyclopedia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {


    @PostMapping("/api/save")
    public boolean saveData() {

        return true;
    }

}
