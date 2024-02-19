package com.zerobase.gardening.event.service;

import com.zerobase.gardening.event.dto.EventCheckDto;
import com.zerobase.gardening.event.dto.EventDto;
import com.zerobase.gardening.event.dto.EventEditDto;
import com.zerobase.gardening.event.entity.Event;
import com.zerobase.gardening.event.mapper.EventMapper;
import com.zerobase.gardening.event.repository.EventRepository;
import com.zerobase.gardening.exception.CustomException;
import com.zerobase.gardening.plant.repository.PlantRepository;
import com.zerobase.gardening.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zerobase.gardening.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements  EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;

    @Override
    public boolean register(EventDto eventDto) {
        // 회원 확인
        userRepository.findById(eventDto.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 등록된 식물인지 확인
        plantRepository.findById(eventDto.getPlantId())
                .orElseThrow(() -> new CustomException(PLANT_NOT_FOUND));

        // 날짜 확인
        if (eventDto.getEventDate().isBefore(LocalDate.now())) {
            throw new CustomException(EVENT_DATE_ERROR);
        }

        // dto -> entity
        Event event = eventMapper.toEvent(eventDto);

        return !ObjectUtils.isEmpty(eventRepository.save(event));
    }

    @Override
    public boolean edit(EventEditDto editDto) {
        // 날짜 확인
        if (editDto.getEventDate().isBefore(LocalDate.now())) {
            throw new CustomException(EVENT_DATE_ERROR);
        }

        // event entity 조회
        Event event = eventRepository.findById(editDto.getId())
                .orElseThrow(() -> new CustomException(EVENT_NOT_FOUND));

        // mapping
        eventMapper.updateEvent(editDto, event);

        return !ObjectUtils.isEmpty(eventRepository.save(event));
    }

    @Override
    public void delete(long id) {
        // event entity 조회
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new CustomException(EVENT_NOT_FOUND));

        // 삭제
        eventRepository.delete(event);
    }

    @Override
    @Transactional
    public boolean check(EventCheckDto checkDto) {
        // 회원 조회
        userRepository.findById(checkDto.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        List<Event> events = new ArrayList<>();

        // 이벤트의 checked 값 업데이트
        for (Map.Entry<Long, Boolean> entry : checkDto.getCheckList().entrySet()) {
            Event event = eventRepository.findById(entry.getKey())
                    .orElseThrow(() -> new CustomException(EVENT_NOT_FOUND));
            event.setChecked(entry.getValue());
            events.add(event);
        }

       // 업데이트
        try {
            eventRepository.saveAll(events);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
