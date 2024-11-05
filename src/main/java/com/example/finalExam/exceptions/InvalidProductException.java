package com.example.finalExam.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidProductException extends RuntimeException {
    private final static Logger logger = LoggerFactory.getLogger(InvalidProductException.class);

    public InvalidProductException() {
        super(ErrorMessages.PRODUCT_INVALID.getMessage());
        logger.error("Exception {} thrown", getClass());
    }
}
