package com.healthcare.user_management.dto;

import com.healthcare.user_management.model.User;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public UserResponseDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.role = user.getRole().getRoleName().toString();
        this.password = user.getPassword();
    }
}
