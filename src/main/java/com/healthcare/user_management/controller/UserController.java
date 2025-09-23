package com.healthcare.user_management.controller;

import com.healthcare.user_management.dto.NewUserRequestDTO;
import com.healthcare.user_management.dto.UpdateUserDTO;
import com.healthcare.user_management.dto.UserResponseDTO;
import com.healthcare.user_management.service.implementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserServiceImplementation userService;

    @Autowired
    public UserController(UserServiceImplementation userService) {
        this.userService = userService;
    }

    /**
     *
     */
    @GetMapping("get_all")
    public List<UserResponseDTO> getUsers(){
        return userService.getAllUsers();
    }

    /**
     *
     */

    @PostMapping("new")
    public ResponseEntity<?> addNewUser(@RequestBody NewUserRequestDTO newUserRequestDTO){
        return userService.newUser(newUserRequestDTO);
    }

    /**
     * This controller handles put requests to modify user information
     * @param userId
     * @param updateUserDTO
     * @return
     */
    @PutMapping("update")
    public ResponseEntity<?> updateUser(@RequestParam Integer userId, @RequestBody UpdateUserDTO updateUserDTO){
        return userService.updateUser(userId,updateUserDTO);
    }


    /**
     * This method deletes user account
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Integer id){
        return userService.removeUser(id);
    }
}
