package com.healthcare.user_management.controller;

import com.healthcare.user_management.dto.*;
import com.healthcare.user_management.service.implementation.UserServiceImplementation;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse<?>> getUsers(){
        List<UserResponseDTO> users = userService.getAllUsers();
        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(
                true,
                "",
                users
        );
        return ResponseEntity.status(200)
                .body(response);
    }

    /**
     *
     */

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<NewUserResponseDTO>> addNewUser(@Valid @RequestBody NewUserRequestDTO newUserRequestDTO) {
        NewUserResponseDTO user = userService.newUser(newUserRequestDTO);

        ApiResponse<NewUserResponseDTO> response = new ApiResponse<>(
                true,
                "New user added successfully",
                user
        );

        return ResponseEntity.status(201).body(response); // 201 Created
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
