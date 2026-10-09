package com.healthcare.user_management.modules.auth.controller;

import com.healthcare.user_management.modules.auth.dto.LoginRequestDto;
import com.healthcare.user_management.modules.auth.dto.LoginResponseDto;
import com.healthcare.user_management.modules.auth.service.implementation.AuthenticationServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("")
    public ResponseEntity<Object> authenticate(@RequestBody LoginRequestDto requestDto){
        LoginResponseDto response = authenticationService.authenticate(requestDto);

        return ResponseEntity.status(200)
                .body(response);
    }
}
