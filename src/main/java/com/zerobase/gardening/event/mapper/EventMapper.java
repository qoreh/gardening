package com.zerobase.gardening.event.mapper;

import com.zerobase.gardening.event.dto.EventDto;
import com.zerobase.gardening.event.dto.EventEditDto;
import com.zerobase.gardening.event.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEvent(EventDto eventDto);

    void updateEvent(EventEditDto editDto, @MappingTarget Event event);
}
