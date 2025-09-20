package com.healthcare.user_management.dto;

import com.healthcare.user_management.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewUserResponseDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String password;

    public NewUserResponseDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole().getRoleName().toString();
        this.password = user.getPassword();
    }

}
