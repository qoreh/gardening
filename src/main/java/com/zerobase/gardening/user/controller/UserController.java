package com.zerobase.gardening.user.controller;

import com.zerobase.gardening.user.dto.SignInDto;
import com.zerobase.gardening.user.dto.SignUpDto;
import com.zerobase.gardening.user.dto.UpdateDto;
import com.zerobase.gardening.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        boolean result = userService.signUp(signUpDto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        String token = userService.signIn(signInDto);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam String email) {
        userService.withDraw(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateDto updateDto) {
        boolean result = userService.update(updateDto);
        return ResponseEntity.ok().body(result);
    }
}
