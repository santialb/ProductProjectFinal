package com.example.finalExam.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    PRODUCT_NOT_FOUND("Product not found");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
