package com.healthcare.user_management.init;

import com.healthcare.user_management.UserManagementApplication;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {

    private static String[] roles = {"DOCTOR","NURSE","PATIENT","RECEPTIONIST"};

    @EventListener(UserManagementApplication.class)
    private void initializeRoles(){

    }
}
