package com.healthcare.user_management.init;

import com.healthcare.user_management.UserManagementApplication;
import com.healthcare.user_management.model.Role;
import com.healthcare.user_management.model.RoleEnum;
import com.healthcare.user_management.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {

    private final RoleRepository roleRepository;

    private static RoleEnum[] roles = {
            RoleEnum.DOCTOR,
            RoleEnum.ADMIN,
            RoleEnum.PATIENT,
            RoleEnum.RECEPTIONIST,
            RoleEnum.NURSE
    };

    @Autowired
    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * This method is an event listener that initialize roles
     */
    @EventListener(ApplicationReadyEvent.class)
    private void initializeRoles(){

        for(RoleEnum roleEnum : roles){
            // Check if the role does not exist
            if(! roleRepository.existsByRoleName(roleEnum)){
                Role role = new Role();
                role.setRoleName(roleEnum);
                // Save the role to the database
                roleRepository.save(role);
            }
        }
    }
}
