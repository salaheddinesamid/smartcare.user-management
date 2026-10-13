package com.healthcare.user_management.modules.user_management.mapper;

import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.modules.user_management.model.Role;
import com.healthcare.user_management.modules.user_management.model.RoleEnum;
import com.healthcare.user_management.modules.user_management.model.User;
import com.healthcare.user_management.modules.user_management.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToUser(NewUserRequestDTO dto){
        User user = new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                null

        );
        dto.getRoles()
                .forEach(r-> {
                    Role role = roleRepository.findByRoleName(RoleEnum.valueOf(r))
                            .orElseThrow();
                    user.getUserRoles().add(role);
                });

        return user;
    }
}
