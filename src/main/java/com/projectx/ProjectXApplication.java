package com.projectx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.projectx"})
public class ProjectXApplication {
    private static final Logger logger = LoggerFactory.getLogger(ProjectXApplication.class);

    public static void main(String[] args) {
        logger.info("Starting ProjectX Application...");
        SpringApplication.run(ProjectXApplication.class, args);
        logger.info("ProjectX Application started successfully");
    }

}
