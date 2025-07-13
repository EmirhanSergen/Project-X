package com.projectx.common.exception;

// Custom exception for invalid input scenarios
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
} 