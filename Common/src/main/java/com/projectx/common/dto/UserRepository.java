package com.projectx.common.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// We handle basic CRUD operations with JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {

    // Optional is used to handle values other than User object
    Optional<User> findByUsername(String username); // Find user by username specific method
} 