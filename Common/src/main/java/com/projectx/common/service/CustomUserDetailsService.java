package com.projectx.common.service;

import com.projectx.common.entity.User;
import com.projectx.common.entity.CustomUserDetails;
import com.projectx.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Loading user details for username: {}", username);
        
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            logger.warn("User not found for username: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
        User user = userOpt.get();
        CustomUserDetails userDetails = new CustomUserDetails(user);
        
        logger.debug("User details loaded successfully for username: {} with roles: {}", 
                    username, user.getRoles());
        
        return userDetails;
    }
} 