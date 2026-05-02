package com.healthcare.user_management.modules.auth.service.implementation;

import com.healthcare.user_management.modules.auth.dto.LoginRequestDto;
import com.healthcare.user_management.modules.auth.dto.LoginResponseDto;
import com.healthcare.user_management.modules.auth.service.AuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationServiceImpl(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public LoginResponseDto authenticate(LoginRequestDto requestDto) {
        // Fetch user details:
        UserDetails user = userDetailsService.findUserDetails(
                requestDto.getUsername()
        );
        return null;
    }
}
