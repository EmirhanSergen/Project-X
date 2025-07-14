package com.projectx.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {
    private static final Logger logger = LoggerFactory.getLogger(CourseApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Course Application...");
        SpringApplication.run(CourseApplication.class, args);
        logger.info("Course Application started successfully");
    }

}
