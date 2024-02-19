package com.zerobase.gardening.event.service;

import com.zerobase.gardening.event.dto.EventCheckDto;
import com.zerobase.gardening.event.dto.EventDto;
import com.zerobase.gardening.event.dto.EventEditDto;

public interface EventService {

    boolean register(EventDto eventDto);

    boolean edit(EventEditDto editDto);

    void delete(long id);

    boolean check(EventCheckDto checkDto);
}
