package com.healthcare.user_management.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found with email:");
    }
}
