package com.healthcare.user_management.exception;

import com.healthcare.user_management.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ApiResponse<?> response = new ApiResponse<>(
                false,
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(409).body(response); // 409 Conflict
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUserNotFound(UserNotFoundException ex){
        ApiResponse<?> response = new ApiResponse<>(
                false,
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(404).body(response); // 404 Conflict
    }
}
