package com.zerobase.gardening.user.mapper;

import com.zerobase.gardening.user.dto.SignUpDto;
import com.zerobase.gardening.user.dto.UpdateDto;
import com.zerobase.gardening.user.entity.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-12T01:34:19+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User dtoToUser(SignUpDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( requestDto.getEmail() );
        user.password( requestDto.getPassword() );
        user.name( requestDto.getName() );
        user.phone( requestDto.getPhone() );
        user.role( requestDto.getRole() );

        return user.build();
    }

    @Override
    public SignUpDto userToDto(User user) {
        if ( user == null ) {
            return null;
        }

        SignUpDto signUpDto = new SignUpDto();

        signUpDto.setEmail( user.getEmail() );
        signUpDto.setPassword( user.getPassword() );
        signUpDto.setName( user.getName() );
        signUpDto.setPhone( user.getPhone() );
        signUpDto.setRole( user.getRole() );

        return signUpDto;
    }

    @Override
    public User updateUserFromDto(UpdateDto updateDto, User user) {
        if ( updateDto == null ) {
            return user;
        }

        user.setId( updateDto.getId() );
        user.setEmail( updateDto.getEmail() );
        user.setPassword( updateDto.getPassword() );
        user.setName( updateDto.getName() );
        user.setPhone( updateDto.getPhone() );

        return user;
    }
}
