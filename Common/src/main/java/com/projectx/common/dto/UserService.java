package com.projectx.common.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Spring annotation for service so that spring can manage this class
@RequiredArgsConstructor // Lombok constructor injection için
public class UserService {

    private final UserRepository userRepository;
    // Spring Securty suggest to use BCryptPasswordEncoder for password hashing
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    
} 