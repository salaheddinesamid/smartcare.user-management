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
    public ResponseEntity<ApiResponse<?>> updateUser(@RequestParam Integer userId, @RequestBody UpdateUserDTO updateUserDTO){
        UserResponseDTO updateUser = userService.updateUser(userId,updateUserDTO);

        ApiResponse<?> response = new ApiResponse<>(
                true,
                "User updated successfully",
                updateUser
        );

        return
                ResponseEntity.status(200)
                        .body(response);
    }


    /**
     * This method deletes user account
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse<?>> removeUser(@PathVariable Integer id){
        userService.removeUser(id);
        return ResponseEntity
                .status(200)
                .body(new ApiResponse<>(
                        true,
                        "The user has been deleted",
                        null
                ));
    }
}
