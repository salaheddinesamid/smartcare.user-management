package com.healthcare.user_management.repo;

import com.healthcare.user_management.model.Role;
import com.healthcare.user_management.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByRoleName(RoleEnum roleName);
    boolean existsByRoleName(RoleEnum roleEnum);
}
