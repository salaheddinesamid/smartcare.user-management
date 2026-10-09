package com.healthcare.user_management.modules.user_management.service.implementation;

import com.healthcare.user_management.dto.UpdateUserDTO;
import com.healthcare.user_management.dto.UserResponseDTO;
import com.healthcare.user_management.exception.UserNotFoundException;
import com.healthcare.user_management.modules.user_management.model.User;
import com.healthcare.user_management.modules.user_management.repository.UserRepository;
import com.healthcare.user_management.modules.user_management.service.UserDetailsUpdaterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUpdaterServiceImpl implements UserDetailsUpdaterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserUpdaterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UpdateUserDTO updateUserDTO) {
        // Fetch the user from the database
        User user = userRepository
                .findById(userId).orElseThrow(UserNotFoundException::new);
        // Update user information:
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setEmail(updateUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));

        // Save the changes
        userRepository.save(user);

        // return the response to the client
        return new UserResponseDTO(user);
    }
}
