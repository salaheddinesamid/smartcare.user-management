package com.healthcare.user_management.modules.user_management.service;

import com.healthcare.user_management.dto.UserResponseDTO;

import java.util.List;

public interface UserQueryService {

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

    /**
     *
     * @param id
     * @return
     */
    UserResponseDTO getUserById(Long id);

    /**
     * This method is used to fetch list of users
     * @param ids
     * @return
     */
    List<UserResponseDTO> getUsers(List<Integer> ids);
}
