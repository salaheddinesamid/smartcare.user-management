package com.healthcare.user_management.dto;

import com.healthcare.user_management.modules.user_management.model.Role;
import com.healthcare.user_management.modules.user_management.model.User;
import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class UserResponseDTO {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> roles;

    public UserResponseDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.roles = null;
        this.password = user.getPassword();
    }
}
