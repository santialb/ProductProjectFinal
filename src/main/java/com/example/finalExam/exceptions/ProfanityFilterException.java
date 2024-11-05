package com.example.finalExam.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProfanityFilterException extends RuntimeException {
    private final static Logger logger = LoggerFactory.getLogger(ProfanityFilterException.class);

    public ProfanityFilterException(){
        super(ErrorMessages.PROFANITY_DOWN.getMessage());
        logger.error("Exception {} thrown", getClass());
    }
}
