package com.healthcare.user_management.modules.auth.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private TokenDto token;
    private UserDetailsDto userDetails;
}
