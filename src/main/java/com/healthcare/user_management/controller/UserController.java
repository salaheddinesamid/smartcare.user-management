package com.healthcare.user_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    /**
     *
     */
    @GetMapping("get_all")
    public List<User>
}
