package com.healthcare.user_management.service.implementation;


import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.dto.NewUserResponseDTO;
import com.healthcare.user_management.dto.UpdateUserDTO;
import com.healthcare.user_management.dto.UserResponseDTO;
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
    public ResponseEntity<?> newUser(NewUserRequestDTO newUserRequestDTO) {

        // Check if the user already exists
        if (userRepository.existsByEmail(newUserRequestDTO.getEmail())){
            return ResponseEntity.status(403).body(
                    Map.of("error","This user already exists")
            );
        }

        // Create new User
        Role role = roleRepository.findByRoleName(RoleEnum.valueOf(newUserRequestDTO.getRoleName()))
                .orElseThrow();
        User user = new User(
                newUserRequestDTO.getFirstName(),
                newUserRequestDTO.getLastName(),
                newUserRequestDTO.getEmail(),
                passwordEncoder.encode(newUserRequestDTO.getPassword()),
                role

        );

        // Save the user:
        userRepository.save(user);

        // Construct the response:
        NewUserResponseDTO newUserResponseDTO = new NewUserResponseDTO(user);

        // return the response to the client:
        return ResponseEntity.status(200)
                .body(newUserResponseDTO);

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
    public ResponseEntity<?> updateUser(Integer id, UpdateUserDTO updateUserDTO) {
        // Fetch the user from the database
        User user = userRepository
                .findById(id).orElseThrow();
        // Update user information:
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setEmail(updateUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));

        // Save the changes
        userRepository.save(user);

        // return the response to the client
        return ResponseEntity
                .status(200).body(new UserResponseDTO(user));
    }

    /**
     * This method used to remove user account from the database
     * @param id
     */
    @Override
    public ResponseEntity<?> removeUser(int id) {
        User user = userRepository.findById(id) // Fetch the user from db, otherwise throw an exception
                .orElseThrow();
        userRepository.delete(user);

        return ResponseEntity.status(200)
                .body(Map.of("message","The user has been deleted"));

    }
}
