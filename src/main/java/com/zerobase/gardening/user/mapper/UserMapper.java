package com.zerobase.gardening.user.mapper;

import com.zerobase.gardening.user.dto.SignUpDto;
import com.zerobase.gardening.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "updateDate", ignore = true)
    User dtoToUser(SignUpDto requestDto);

}
