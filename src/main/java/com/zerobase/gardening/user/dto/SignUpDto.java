package com.zerobase.gardening.user.dto;

import com.zerobase.gardening.type.UserRole;
import lombok.Data;

@Data
public class SignUpDto {

    private String email;

    private String password;

    private String name;

    private String phone;

    private UserRole role;

}
