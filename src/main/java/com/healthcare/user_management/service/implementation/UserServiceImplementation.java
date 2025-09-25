package com.healthcare.user_management.service.implementation;


import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.dto.NewUserResponseDTO;
import com.healthcare.user_management.dto.UpdateUserDTO;
import com.healthcare.user_management.dto.UserResponseDTO;
import com.healthcare.user_management.exception.UserAlreadyExistsException;
import com.healthcare.user_management.exception.UserNotFoundException;
import com.healthcare.user_management.model.Role;
import com.healthcare.user_management.model.RoleEnum;
import com.healthcare.user_management.model.User;
import com.healthcare.user_management.repo.RoleRepository;
import com.healthcare.user_management.repo.UserRepository;
import com.healthcare.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public NewUserResponseDTO newUser(NewUserRequestDTO newUserRequestDTO) {

        if (userRepository.existsByEmail(newUserRequestDTO.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + newUserRequestDTO.getEmail() + " already exists");
        }

        Role role = roleRepository.findByRoleName(RoleEnum.valueOf(newUserRequestDTO.getRoleName().toUpperCase()))
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User(
                newUserRequestDTO.getFirstName(),
                newUserRequestDTO.getLastName(),
                newUserRequestDTO.getEmail(),
                passwordEncoder.encode(newUserRequestDTO.getPassword()),
                role
        );

        userRepository.save(user);
        return new NewUserResponseDTO(user);
    }



    /**
     * This method returns all the users in the database
     * @return List<UserResponseDTO>
     */
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
        return null;
    }

    @Override
    public UserResponseDTO updateUser(Integer id, UpdateUserDTO updateUserDTO) {
        // Fetch the user from the database
        User user = userRepository
                .findById(id).orElseThrow(UserNotFoundException::new);
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

    /**
     * This method used to remove user account from the database
     * @param id
     */
    @Override
    public void removeUser(int id) {
        User user = userRepository.findById(id) // Fetch the user from db, otherwise throw an exception
                .orElseThrow();
        userRepository.delete(user);

    }
}
