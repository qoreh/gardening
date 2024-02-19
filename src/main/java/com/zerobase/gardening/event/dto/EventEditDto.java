package com.zerobase.gardening.event.dto;

import com.zerobase.gardening.event.type.Type;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventEditDto {

    private long id;

    private Type type;

    private String description;

    private LocalDate eventDate;
}
