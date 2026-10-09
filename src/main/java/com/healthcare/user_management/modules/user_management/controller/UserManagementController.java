package com.healthcare.user_management.modules.user_management.controller;

import com.healthcare.user_management.dto.*;
import com.healthcare.user_management.modules.user_management.service.implementation.UserQueryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserManagementController {

    private final UserQueryServiceImpl userQueryService;

    public UserManagementController(UserQueryServiceImpl userQueryService) {
        this.userQueryService = userQueryService;
    }

    /**
     *
     */
    @GetMapping("get_all")
    public ResponseEntity<ApiResponse<?>> getUsers(){
        List<UserResponseDTO> users = userQueryService.getAllUsers();
        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(
                true,
                "",
                users
        );
        return ResponseEntity.status(200)
                .body(response);
    }

    @GetMapping("get")
    public ResponseEntity<ApiResponse<?>> fetchUser(@RequestParam String email){
        UserResponseDTO user = userQueryService.getUser(email);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                true,
                "",
                user
        );
        return ResponseEntity.status(200)
                .body(response);
    }

    @GetMapping("get_by_id")
    public ResponseEntity<ApiResponse<?>> fetchUserById(@RequestParam Integer userId){
        UserResponseDTO user = userQueryService.getUserById(userId);

        ApiResponse<?> response = new ApiResponse<>(
                true,
                "",
                user
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

    @GetMapping("/exists")
    public ResponseEntity<ApiResponse<?>> checkUserExistence(@RequestParam String email){
        boolean userExists = userService.checkExistence(email);
        ApiResponse<?> response = new ApiResponse<>(
                true,
                "",
                userExists
        );

        return ResponseEntity.status(200)
                .body(response);
    }

    @GetMapping("verify")
    public ResponseEntity<ApiResponse<?>> verify(@RequestParam String email, @RequestParam String password){
        boolean userVerified = userService.verifyUserCredentials(email,password);

        ApiResponse<?> response = new ApiResponse<>(
                true,
                "The user credentials are correct",
                userVerified
        );

        return ResponseEntity
                .status(200)
                .body(response);
    }

    @PostMapping("get-users")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsers(@RequestBody List<Integer> ids){
        List<UserResponseDTO> users = userService
                .getUsers(ids);

        return ResponseEntity
                .status(200)
                .body(new ApiResponse<>(
                        true,
                        "",
                        users
                ));
    }
}
