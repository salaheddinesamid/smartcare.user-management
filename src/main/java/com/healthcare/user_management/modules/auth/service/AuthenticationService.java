package com.healthcare.user_management.modules.auth.service;

import com.healthcare.user_management.modules.auth.dto.LoginRequestDto;
import com.healthcare.user_management.modules.auth.dto.LoginResponseDto;

public interface AuthenticationService {
    /**
     *
     * @param requestDto
     * @return
     */
    LoginResponseDto authenticate(LoginRequestDto requestDto);
}
