package com.zerobase.gardening.user.service;

import com.zerobase.gardening.exception.CustomException;
import com.zerobase.gardening.security.JwtTokenProvider;
import com.zerobase.gardening.user.dto.SignInDto;
import com.zerobase.gardening.user.dto.SignUpDto;
import com.zerobase.gardening.user.dto.UpdateDto;
import com.zerobase.gardening.user.entity.User;
import com.zerobase.gardening.user.mapper.UserMapper;
import com.zerobase.gardening.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

import static com.zerobase.gardening.type.ErrorCode.*;
import static com.zerobase.gardening.type.UserStatus.USER_STATUS_ING;
import static com.zerobase.gardening.type.UserStatus.USER_STATUS_WITHDRAW;

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
        userRepository.findByEmail(signUpDto.getEmail())
                .ifPresent(user -> {
                    throw new CustomException(ALREADY_EXIST_USER);
                });

        // 패스워드 암호화
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // DTO -> ENTITY 맵핑
        User saveUser = userMapper.dtoToUser(signUpDto);
        saveUser.setRegisterDateTime(LocalDateTime.now());
        saveUser.setStatus(USER_STATUS_ING);

        // Repository 저장 && 성공 여부 반환
        return !ObjectUtils.isEmpty(userRepository.save(saveUser));
    }

    @Override
    public String signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new CustomException((ALREADY_EXIST_USER)));

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
                .generateToken(user.getEmail(), user.getRole().getRole());
    }

    @Override
    public void withDraw(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException((ALREADY_EXIST_USER)));

        // 회원 상태 체크
        if (user.getStatus() == USER_STATUS_WITHDRAW) {
            throw new CustomException(ALREADY_WITHDRAW_USER);
        }

        // 탈퇴 상태 변경
        user.setStatus(USER_STATUS_WITHDRAW);
        userRepository.save(user);
    }

    @Override
    public boolean update(UpdateDto updateDto) {
        User user = userRepository.findById(updateDto.getId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 패스워드 암호화
        updateDto.setPassword(passwordEncoder.encode(updateDto.getPassword()));

        // Dto -> Entity 업데이트
        User updatedUser = userMapper.updateUserFromDto(updateDto, user);
        updatedUser.setUpdateDateTime(LocalDateTime.now());

        return !ObjectUtils.isEmpty(userRepository.save(updatedUser));
    }


}
