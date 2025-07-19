package com.projectx.root.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);





    // Test endpoint for role-based authorization
    @GetMapping("/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Admin endpoint accessed by user: {} with authorities: {}", 
                   auth.getName(), auth.getAuthorities());
        return ResponseEntity.ok("Admin access granted!");
    }

    @GetMapping("/test/member")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<String> memberOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Member endpoint accessed by user: {} with authorities: {}", 
                   auth.getName(), auth.getAuthorities());
        return ResponseEntity.ok("Member access granted!");
    }

    @GetMapping("/test/authenticated")
    public ResponseEntity<String> authenticatedOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authenticated endpoint accessed by user: {} with authorities: {}", 
                   auth.getName(), auth.getAuthorities());
        return ResponseEntity.ok("Authenticated access granted!");
    }
} 