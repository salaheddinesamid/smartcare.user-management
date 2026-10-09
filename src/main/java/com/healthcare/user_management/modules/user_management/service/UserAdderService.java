package com.healthcare.user_management.modules.user_management.service;

import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.dto.NewUserResponseDTO;

public interface UserAdderService {

    /**
     *
     * @param newUserRequestDTO
     * @return
     */
    NewUserResponseDTO addUser(NewUserRequestDTO newUserRequestDTO);
}
