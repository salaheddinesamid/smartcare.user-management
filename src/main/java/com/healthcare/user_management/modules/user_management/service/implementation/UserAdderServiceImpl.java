package com.healthcare.user_management.modules.user_management.service.implementation;

import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.dto.NewUserResponseDTO;
import com.healthcare.user_management.exception.UserAlreadyExistsException;
import com.healthcare.user_management.modules.user_management.model.Role;
import com.healthcare.user_management.modules.user_management.model.RoleEnum;
import com.healthcare.user_management.modules.user_management.model.User;
import com.healthcare.user_management.modules.user_management.repository.RoleRepository;
import com.healthcare.user_management.modules.user_management.repository.UserRepository;
import com.healthcare.user_management.modules.user_management.service.UserAdderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAdderServiceImpl implements UserAdderService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAdderServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public NewUserResponseDTO addUser(NewUserRequestDTO newUserRequestDTO) {
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
}
