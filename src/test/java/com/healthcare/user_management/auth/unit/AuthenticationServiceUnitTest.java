package com.healthcare.user_management.auth.unit;

import com.healthcare.user_management.auth.MockedUser;
import com.healthcare.user_management.modules.auth.dto.LoginRequestDto;
import com.healthcare.user_management.modules.auth.dto.LoginResponseDto;
import com.healthcare.user_management.modules.auth.service.implementation.AuthenticationServiceImpl;
import com.healthcare.user_management.modules.auth.service.implementation.UserDetailsServiceImpl;
import com.healthcare.user_management.modules.auth.utils.JwtUtilities;
import com.healthcare.user_management.modules.user_management.model.User;
import com.healthcare.user_management.modules.user_management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class AuthenticationServiceUnitTest {

    @Mock
    private JwtUtilities jwtUtilities;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    private User mockedUser;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockedUser = new User(
                "John",
                "Doe",
                "john.doe@smartcare.com",
                "",
                List.of()
        );
    }

    @Test
    void testAuthenticationSuccess(){
        when(userDetailsService.loadUserByUsername("john.doe@smartcare.com"))
                .thenReturn(mockedUser);
        when(userRepository.existsByEmail("john.doe@smartcare.com"))
                .thenReturn(true);

        LoginResponseDto response = authenticationService.authenticate(
                new LoginRequestDto(
                        "john.doe@smartcare.com",
                        "correctPassword"
                )
        );
        assertNotNull(response.accessToken());
    }

    @Test
    void testAuthenticationFailureThrowsInvalidCredentials(){
        when(userDetailsService.loadUserByUsername("john.doe@smartcare.com"))
                .thenReturn(mockedUser);


    }
}
