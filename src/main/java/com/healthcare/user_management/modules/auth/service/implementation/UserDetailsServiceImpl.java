package com.healthcare.user_management.modules.auth.service.implementation;

import com.healthcare.user_management.exception.UserNotFoundException;
import com.healthcare.user_management.modules.auth.service.UserDetailsService;
import com.healthcare.user_management.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails findUserDetails(String userName) {
        // Fetch user details from the database:
        return userRepository.findByEmail(userName)
                .orElseThrow(UserNotFoundException::new);
    }
}
