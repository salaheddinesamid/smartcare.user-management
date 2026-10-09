package com.healthcare.user_management.modules.user_management.service;

import com.healthcare.user_management.dto.UpdateUserDTO;
import com.healthcare.user_management.dto.UserResponseDTO;

public interface UserDetailsUpdaterService {

    UserResponseDTO updateUser(Long userId, UpdateUserDTO updateUserDTO);
}
