package com.healthcare.user_management.exception;

import com.healthcare.user_management.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserException {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> userAlreadyExists(String message){
        ApiResponse<?> response = new ApiResponse<>(
                false,
                "This user already exists",
                null
        );

        return ResponseEntity.status(405)
                .body(response);
    }
}
