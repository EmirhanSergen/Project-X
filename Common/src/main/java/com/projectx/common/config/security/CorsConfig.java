package com.projectx.common.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

// Class for CORS configuration
@Configuration
public class CorsConfig {
    private static final Logger logger = LoggerFactory.getLogger(CorsConfig.class);

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        logger.info("Creating CORS Configuration Source");
        
        // pre build configuration for CORS comes from spring boot
        CorsConfiguration configuration = new CorsConfiguration();
        
        // For development we allow all origins but in production we should specify the origins
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));
        
    
        configuration.setAllowedHeaders(Arrays.asList(
            "Origin", "Content-Type", "Accept", "Authorization", 
            "X-Requested-With", "Cache-Control", "Pragma"
        ));
        
        // Exposed headers are the headers that are exposed to the client
        // I didn't understand this part ?? 
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization", "Content-Type", "X-Total-Count"
        ));
        
        // Allow credentials (cookies, authorization headers) to be sent
        configuration.setAllowCredentials(true);
        
        // Max age of the preflight request . Need to consider this part ? 
        configuration.setMaxAge(3600L);
        
        // Apply the CORS configuration to the URL pattern
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        logger.info("CORS Configuration Source created successfully");
        logger.debug("Allowed Origins: {}", configuration.getAllowedOriginPatterns());
        logger.debug("Allowed Methods: {}", configuration.getAllowedMethods());
        logger.debug("Allowed Headers: {}", configuration.getAllowedHeaders());
        
        return source;
    }
} 