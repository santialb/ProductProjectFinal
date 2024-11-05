package com.example.finalExam.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(productNotFoundException.getMessage()));
    }


    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProductException(InvalidProductException invalidProductException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(invalidProductException.getMessage()));
    }

    @ExceptionHandler(ProfanityFilterException.class)
    public ResponseEntity<ErrorResponse> handleProfanityFilterException(ProfanityFilterException profanityFilterException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(profanityFilterException.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductNotValidConstraints(ConstraintViolationException exception){
        return new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage());
    }




}
