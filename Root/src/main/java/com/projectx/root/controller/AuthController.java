package com.projectx.root.controller;

import com.projectx.common.dto.User;
import com.projectx.common.dto.UserService;
import com.projectx.common.exception.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Input validation forusername and password must not be null or empty
        // We throw an exception from global exception handler  with our custom exception
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new InvalidInputException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Password cannot be empty");
        }
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }
} 