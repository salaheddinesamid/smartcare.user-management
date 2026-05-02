package com.healthcare.user_management.modules.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    /**
     *
     * @param userName
     * @return
     */
    UserDetails findUserDetails(String userName);
}
