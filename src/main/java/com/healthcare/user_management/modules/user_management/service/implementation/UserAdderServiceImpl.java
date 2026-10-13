package com.healthcare.user_management.modules.user_management.service.implementation;

import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.dto.NewUserResponseDTO;
import com.healthcare.user_management.exception.UserAlreadyExistsException;
import com.healthcare.user_management.modules.user_management.mapper.UserMapper;
import com.healthcare.user_management.modules.user_management.model.User;
import com.healthcare.user_management.modules.user_management.repository.UserRepository;
import com.healthcare.user_management.modules.user_management.service.UserAdderService;
import org.springframework.stereotype.Service;

@Service
public class UserAdderServiceImpl implements UserAdderService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserAdderServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public NewUserResponseDTO addUser(NewUserRequestDTO newUserRequestDTO) {
        if (userRepository.existsByEmail(newUserRequestDTO.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + newUserRequestDTO.getEmail() + " already exists");
        }
        User user = userMapper.mapToUser(newUserRequestDTO);

        userRepository.save(user);
        return new NewUserResponseDTO(user);
    }
}
