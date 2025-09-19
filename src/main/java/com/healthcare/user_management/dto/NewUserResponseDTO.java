package com.healthcare.user_management.dto;

import lombok.Data;

@Data
public class NewUserResponseDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;

}
