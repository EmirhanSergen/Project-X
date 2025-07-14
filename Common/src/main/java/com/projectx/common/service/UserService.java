package com.projectx.common.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.projectx.common.dto.LoginRequest;
import com.projectx.common.dto.LoginResponse;
import com.projectx.common.entity.User;
import com.projectx.common.repository.UserRepository;
import com.projectx.common.exception.InvalidInputException;
import java.util.Optional;
import com.projectx.common.utils.AuthUtil;


// Where does should be user repository  and user service ? 
@Service // Spring annotation for service so that spring can manage this class
@RequiredArgsConstructor // Lombok constructor injection için
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    // Spring Securty suggest to use BCryptPasswordEncoder for password hashing
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AuthUtil authUtil;

    public User registerUser(User user) {
        logger.info("Registering new user with username: {}", user.getUsername());
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully with ID: {}", savedUser.getId());
        return savedUser;
    }


    // Should we add password validation to service and throw exception we have already in controller control ?  
    public LoginResponse login(LoginRequest loginRequest) {
        logger.info("Login attempt for username: {}", loginRequest.getUsername());
        
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        if (userOpt.isEmpty()) {
            logger.warn("Login failed: User not found for username: {}", loginRequest.getUsername());
            throw new InvalidInputException("User not found");
        }
        
        User user = userOpt.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            logger.warn("Login failed: Invalid password for username: {}", loginRequest.getUsername());
            throw new InvalidInputException("Invalid password");
        }
        
        String token = authUtil.generateToken(user.getUsername());
        logger.info("Login successful for username: {}", loginRequest.getUsername());
        return new LoginResponse(token);
    }
} 