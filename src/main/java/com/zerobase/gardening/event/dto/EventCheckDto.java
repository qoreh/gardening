package com.zerobase.gardening.event.dto;

import lombok.Data;

import java.util.Map;

@Data
public class EventCheckDto {

    private Map<Long, Boolean> checkList;

    private long userId;
}
