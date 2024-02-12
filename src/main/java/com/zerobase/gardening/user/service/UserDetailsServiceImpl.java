package com.zerobase.gardening.user.service;

import com.zerobase.gardening.exception.CustomException;
import com.zerobase.gardening.user.mapper.UserMapper;
import com.zerobase.gardening.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.zerobase.gardening.type.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper mapper = UserMapper.INSTANCE;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        val user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return mapper.userToDto(user);
    }
}
