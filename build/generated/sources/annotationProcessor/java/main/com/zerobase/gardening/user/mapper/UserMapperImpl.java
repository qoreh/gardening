package com.zerobase.gardening.user.mapper;

import com.zerobase.gardening.user.dto.SignUpDto;
import com.zerobase.gardening.user.entity.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-08T17:27:59+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.9 (Homebrew)"
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
        if ( requestDto.getRole() != null ) {
            user.role( requestDto.getRole().name() );
        }

        return user.build();
    }
}
