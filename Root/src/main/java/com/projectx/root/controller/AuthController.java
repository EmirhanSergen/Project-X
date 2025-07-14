package com.projectx.root.controller;

import com.projectx.common.entity.User;
import com.projectx.common.service.UserService;
import com.projectx.common.dto.LoginRequest;
import com.projectx.common.exception.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        logger.info("Register request received for username: {}", user.getUsername());
        
        // Input validation forusername and password must not be null or empty
        // We throw an exception from global exception handler  with our custom exception
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            logger.warn("Register failed: Username is empty");
            throw new InvalidInputException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            logger.warn("Register failed: Password is empty for username: {}", user.getUsername());
            throw new InvalidInputException("Password cannot be empty");
        }
        
        User savedUser = userService.registerUser(user);
        logger.info("Register successful for username: {} with ID: {}", user.getUsername(), savedUser.getId());
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login request received for username: {}", loginRequest.getUsername());
        
        // Input validation
        if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()) {
            logger.warn("Login failed: Username is empty");
            throw new InvalidInputException("Username cannot be empty");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            logger.warn("Login failed: Password is empty for username: {}", loginRequest.getUsername());
            throw new InvalidInputException("Password cannot be empty");
        }
        
        User user = userService.login(loginRequest);
        logger.info("Login request processed successfully for username: {}", loginRequest.getUsername());
        return ResponseEntity.ok(user);
    }
} 