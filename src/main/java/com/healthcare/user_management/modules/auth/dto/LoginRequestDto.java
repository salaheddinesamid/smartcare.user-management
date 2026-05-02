package com.healthcare.user_management.modules.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String username;
    private String password;
}
