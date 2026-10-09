package com.healthcare.user_management.controller;

import com.healthcare.user_management.dto.*;
import com.healthcare.user_management.service.implementation.UserServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserServiceImplementation userService;

    @Autowired
    public UserController(UserServiceImplementation userService) {
        this.userService = userService;
    }


}
