package com.healthcare.user_management.auth;

import com.healthcare.user_management.modules.user_management.model.User;

public final class MockedUser {

    private MockedUser() {}

    public static User initUser(){
        User user = new User();
        user.setUserId(1);
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword123");
        return user;
    }
}
