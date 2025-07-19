package com.projectx.root.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {
    
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/public")
    public Map<String, Object> publicEndpoint() {
        logger.info("Public endpoint accessed");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bu endpoint herkese açık!");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @GetMapping("/admin")
    public Map<String, Object> adminEndpoint() {
        logger.info("Admin endpoint accessed");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Admin endpoint - Hoş geldiniz!");
        response.put("user", auth.getName());
        response.put("authorities", auth.getAuthorities());
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @GetMapping("/member")
    public Map<String, Object> memberEndpoint() {
        logger.info("Member endpoint accessed");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Member endpoint - Hoş geldiniz!");
        response.put("user", auth.getName());
        response.put("authorities", auth.getAuthorities());
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @GetMapping("/user-info")
    public Map<String, Object> userInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", auth.isAuthenticated());
        response.put("username", auth.getName());
        response.put("authorities", auth.getAuthorities());
        response.put("principal", auth.getPrincipal());
        return response;
    }
    
    @GetMapping("/login")
    public Map<String, Object> loginInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Use Keycloak for authentication");
        response.put("loginUrl", "/oauth2/authorization/keycloak");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
} 