package com.healthcare.user_management.modules.user_management.repository;

import com.healthcare.user_management.modules.user_management.model.Role;
import com.healthcare.user_management.modules.user_management.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleName(RoleEnum roleName);
    Optional<Role> findByRoleName(RoleEnum roleEnum);
}
