package com.zerobase.gardening.user.dto;

import lombok.Data;

@Data
public class UpdateDto {

    private long id;

    private String email;

    private String password;

    private String name;

    private String phone;

}
