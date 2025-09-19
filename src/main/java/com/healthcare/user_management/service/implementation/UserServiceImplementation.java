package com.healthcare.user_management.service.implementation;


import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.dto.NewUserResponseDTO;
import com.healthcare.user_management.dto.UserResponseDTO;
import com.healthcare.user_management.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Override
    public NewUserResponseDTO newUser(NewUserRequestDTO newUserRequestDTO) {
        return null;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponseDTO getUser(String email) {
        return null;
    }

    @Override
    public void removeUser(int id) {

    }
}
