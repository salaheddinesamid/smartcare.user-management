package com.healthcare.user_management.modules.auth.exception;

import com.healthcare.user_management.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class AuthenticationExceptionController {

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<Object> handleFailedAuthentication(){
        return ResponseEntity.status(403)
                .body(Map.of(
                        "", ""
                ));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse> handleInvalidCredentialsException(
            InvalidCredentialsException exception
    ){
        return ResponseEntity.status(403)
                .body(new ApiResponse(
                        false,
                        exception.getMessage(),
                        null
                ));
    }
}
