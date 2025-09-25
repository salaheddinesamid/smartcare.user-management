package com.healthcare.user_management.unit;

import com.healthcare.user_management.repo.UserRepository;
import com.healthcare.user_management.service.implementation.UserServiceImplementation;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * This class is used to write unit testing of user service methods
 */
public class UserServiceUnitTesting {

    @Mock
    private UserServiceImplementation userServiceImplementation;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    /**
     *
     */
    @Test
    void testCreateNewUser(){

    }
}
