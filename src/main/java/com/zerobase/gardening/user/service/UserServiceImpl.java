package com.zerobase.gardening.user.service;

import com.zerobase.gardening.exception.CustomException;
import com.zerobase.gardening.security.JwtTokenProvider;
import com.zerobase.gardening.user.dto.SignInDto;
import com.zerobase.gardening.user.dto.SignUpDto;
import com.zerobase.gardening.user.entity.User;
import com.zerobase.gardening.user.mapper.UserMapper;
import com.zerobase.gardening.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

import static com.zerobase.gardening.type.ErrorCode.*;
import static com.zerobase.gardening.type.UserStatus.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public boolean signUp(SignUpDto signUpDto) {
        // 이메일 중복 검사
        User user = userRepository.findByEmail(signUpDto.getEmail());
        if (!ObjectUtils.isEmpty(user)) {
            throw new CustomException(ALREADY_EXIST_USER);
        }

        // 패스워드 암호화
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // DTO -> ENTITY 맵핑
        User saveUser = userMapper.dtoToUser(signUpDto);
        saveUser.setRegDate(LocalDateTime.now());
        saveUser.setStatus(USER_STATUS_ING.getCode());

        // Repository 저장 && 성공 여부 반환
        return !ObjectUtils.isEmpty(userRepository.save(saveUser));
    }

    @Override
    public String signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail());

        // 회원 존재 여부 체크
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException(INVALID_EMAIL);
        }

        // 패스워드 매칭
        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new CustomException(PASSWORD_MISMATCH);
        }

        // 토큰 발급 & 반환
        return jwtTokenProvider
                .generateToken(user.getEmail(), user.getRole());
    }

    @Override
    public void withDraw(String email) {
        User user = userRepository.findByEmail(email);

        // 회원 상태 체크
        if (user.getStatus().equals(USER_STATUS_WITHDRAW.getCode())) {
            throw new CustomException(ALREADY_WITHDRAW_USER);
        }

        // 탈퇴 상태 변경
        user.setStatus(USER_STATUS_WITHDRAW.getCode());
        userRepository.save(user);
    }


}
