package com.healthcare.user_management.repo;

import com.healthcare.user_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
}
