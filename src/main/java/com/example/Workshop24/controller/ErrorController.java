package com.example.Workshop24.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Workshop24.exception.CartErrorResponse;
import com.example.Workshop24.exception.TransactionFailedException;

// @ControllerAdvice
@RestControllerAdvice
public class ErrorController {
    
    @ExceptionHandler(value={TransactionFailedException.class})
    public ResponseEntity<String> handleException(TransactionFailedException e) {

        CartErrorResponse error = new CartErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return ResponseEntity
                .status(error.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(error.toJson().toString());
    }
}
