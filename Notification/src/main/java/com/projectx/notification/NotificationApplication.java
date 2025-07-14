package com.projectx.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApplication {
    private static final Logger logger = LoggerFactory.getLogger(NotificationApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Notification Application...");
        SpringApplication.run(NotificationApplication.class, args);
        logger.info("Notification Application started successfully");
    }

}
