package com.zerobase.gardening.event.dto;

import com.zerobase.gardening.event.type.Type;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDto {

    private Type type;

    private LocalDate eventDate;

    private String description;

    private long plantId;

    private long userId;
}
