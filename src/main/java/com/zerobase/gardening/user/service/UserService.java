package com.zerobase.gardening.user.service;

import com.zerobase.gardening.user.dto.SignInDto;
import com.zerobase.gardening.user.dto.SignUpDto;

public interface UserService {
    boolean signUp(SignUpDto signUpDto);

    String signIn(SignInDto signInDto);

    void withDraw(String email);

}
