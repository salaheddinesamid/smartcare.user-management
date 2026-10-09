package com.healthcare.user_management.modules.user_management.service.implementation;

import com.healthcare.user_management.dto.UserResponseDTO;
import com.healthcare.user_management.modules.user_management.model.User;
import com.healthcare.user_management.modules.user_management.repository.UserRepository;
import com.healthcare.user_management.modules.user_management.service.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        // fetch all the users from the database
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Override
    public UserResponseDTO getUser(String email) {
        User user =
                userRepository.findByEmail(email)
                        .orElseThrow();

        return new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUserById(Long userId){
        User user =
                userRepository.findById(userId).orElseThrow();

        return new UserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getUsers(List<Integer> ids) {

        // Fetch users and filter them based on ids:
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> ids.contains(user.getUserId()))
                .toList();

        return users.stream()
                .map(UserResponseDTO::new)
                .toList();

    }
}
