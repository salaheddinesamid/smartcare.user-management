package com.healthcare.user_management.modules.auth.service.implementation;

import com.healthcare.user_management.exception.UserNotFoundException;
import com.healthcare.user_management.modules.auth.dto.LoginRequestDto;
import com.healthcare.user_management.modules.auth.dto.LoginResponseDto;
import com.healthcare.user_management.modules.auth.exception.AuthenticationFailedException;
import com.healthcare.user_management.modules.auth.exception.InvalidCredentialsException;
import com.healthcare.user_management.modules.auth.service.AuthenticationService;
import com.healthcare.user_management.modules.auth.utils.JwtUtilities;
import com.healthcare.user_management.modules.user_management.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtilities jwtUtilities;

    public AuthenticationServiceImpl(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository, JwtUtilities jwtUtilities) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtilities = jwtUtilities;
    }

    @Override
    public LoginResponseDto authenticate(LoginRequestDto requestDto) {
        // Check if the user's existence:
        if(!userRepository.existsByEmail(requestDto.getUsername())){
            throw new UserNotFoundException();
        }
        // Fetch user details:
        UserDetails user = userDetailsService.loadUserByUsername(
                requestDto.getUsername()
        );

        assert user != null;
        // Password and account lock check
        if(passwordEncoder.matches(user.getPassword(), requestDto.getPassword())){
            throw new InvalidCredentialsException("Invalid password or username");
        };

        // If the user is authenticated, generate access and refresh tokens:
        String accessToken = jwtUtilities.generateToken(
                user.getUsername(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
        );
        String refreshToken = jwtUtilities.generateRefreshToken();

        return new LoginResponseDto(

        );
    }
}
