package com.healthcare.user_management.exception;

public class UserAlreadyExistsException extends RuntimeException {

    @Override
    public String getMessage() {
        return "This user already exists";
    }
}
