package com.healthcare.user_management.dto;


import lombok.Data;

import java.util.List;

@Data
public class NewUserRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> roles;
}
