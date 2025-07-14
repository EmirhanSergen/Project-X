package com.projectx.common.repository;

import com.projectx.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Where does should be user repository  and user service ? 
// We handle basic CRUD operations with JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {

    // Optional is used to handle values other than User object
    Optional<User> findByUsername(String username); // Find user by username specific method
} 