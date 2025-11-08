package com.healthcare.user_management.service;

import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.dto.NewUserResponseDTO;
import com.healthcare.user_management.dto.UpdateUserDTO;
import com.healthcare.user_management.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    /**
     * This method is used to create new user and save it in the database
     * @param newUserRequestDTO
     * @return a newUserResponseDTO object that contains user information
     */
    NewUserResponseDTO newUser(NewUserRequestDTO newUserRequestDTO);

    /**
     * This method returns all the existing users
     * @return UserResponseDTO
     */
    List<UserResponseDTO> getAllUsers();

    /**
     * This method returns a user based on the email
     * @param email
     * @return
     */
    UserResponseDTO getUser(String email);

    UserResponseDTO getUserById(Integer id);

    /**
     * This method update the user information
     * @param updateUserDTO
     * @param id
     * @return UserResponseDTO with the new user information
     */
    UserResponseDTO updateUser(Integer id,UpdateUserDTO updateUserDTO);


    /**
     * This method is responsible for removing a given user based on the ID
     * @param id
     */
    void removeUser(int id);

    boolean checkExistence(String email);
    boolean verifyUserCredentials(String email, String password);


    /**
     * This method is used to fetch list of users
     * @param ids
     * @return
     */
    List<UserResponseDTO> getUsers(List<Integer> ids);
}
