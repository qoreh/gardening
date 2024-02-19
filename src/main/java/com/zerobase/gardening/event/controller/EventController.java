package com.zerobase.gardening.event.controller;

import com.zerobase.gardening.event.dto.EventCheckDto;
import com.zerobase.gardening.event.dto.EventDto;
import com.zerobase.gardening.event.dto.EventEditDto;
import com.zerobase.gardening.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/event/register")
    public ResponseEntity<?> register(@RequestBody EventDto eventDto) {
        boolean result = eventService.register(eventDto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/event/edit")
    public ResponseEntity<?> edit(@RequestBody EventEditDto editDto) {
        boolean result = eventService.edit(editDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/event/delete")
    public ResponseEntity<?> delete(@RequestParam long id) {
        eventService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/event/check")
    public ResponseEntity<?> check(@RequestBody EventCheckDto checkDto) {
        boolean result = eventService.check(checkDto);
        return ResponseEntity.ok().body(result);
    }



}
