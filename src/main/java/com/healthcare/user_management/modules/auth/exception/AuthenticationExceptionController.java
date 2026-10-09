package com.healthcare.user_management.modules.auth.exception;

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
}
