package com.projectx.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RootApplication {
    private static final Logger logger = LoggerFactory.getLogger(RootApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Root Application...");
        SpringApplication.run(RootApplication.class, args);
        logger.info("Root Application started successfully");
    }

}
