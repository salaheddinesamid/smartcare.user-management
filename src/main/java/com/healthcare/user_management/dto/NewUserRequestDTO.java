package com.healthcare.user_management.dto;


import lombok.Data;

@Data
public class NewUserRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
