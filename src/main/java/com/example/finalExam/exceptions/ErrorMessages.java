package com.example.finalExam.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    PRODUCT_NOT_FOUND("Product not found"),
    PRODUCT_INVALID("Product is not valid, category or region may not be available or you are using explicit words"),
    PROFANITY_DOWN("Profanity page is down");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
