package com.projectx.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = {"com.projectx.root", "com.projectx.common"})
@EnableJpaRepositories(basePackages = {"com.projectx.common.repository"})
@EntityScan(basePackages = {"com.projectx.common.entity"})
public class RootApplication {
    private static final Logger logger = LoggerFactory.getLogger(RootApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Root Application...");
        SpringApplication.run(RootApplication.class, args);
        logger.info("Root Application started successfully");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
