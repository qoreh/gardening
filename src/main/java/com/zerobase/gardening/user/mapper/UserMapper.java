package com.zerobase.gardening.user.mapper;

import com.zerobase.gardening.user.dto.SignUpDto;
import com.zerobase.gardening.user.dto.UpdateDto;
import com.zerobase.gardening.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "updateDateTime", ignore = true)
    User dtoToUser(SignUpDto requestDto);

    SignUpDto userToDto(User user);

    User updateUserFromDto(UpdateDto updateDto, @MappingTarget User user);
}
