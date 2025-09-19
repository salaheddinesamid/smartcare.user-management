package com.healthcare.user_management.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
