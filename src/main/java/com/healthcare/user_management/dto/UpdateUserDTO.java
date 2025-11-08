package com.healthcare.user_management.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
