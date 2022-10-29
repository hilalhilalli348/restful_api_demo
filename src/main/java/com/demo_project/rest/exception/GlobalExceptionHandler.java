package com.demo_project.rest.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFountException.class})
    public ResponseEntity exceptionHandle(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(),exception.getMessage()));
    }

    @ExceptionHandler({ResourceIsEmptyException.class})
    public ResponseEntity exceptionHandle(ResourceIsEmptyException exception) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorDetails(HttpStatus.OK.value(),exception.getMessage()));
    }

    @ExceptionHandler({ResourceConflictException.class})
    public ResponseEntity exceptionHandle(ResourceConflictException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDetails(HttpStatus.CONFLICT.value(),exception.getMessage()));
    }

    @ExceptionHandler({ResourceIsInvalidException.class})
    public ResponseEntity exceptionHandle(ResourceIsInvalidException exception) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorDetails(HttpStatus.UNPROCESSABLE_ENTITY.value(),exception.getMessage()));
    }

    @ExceptionHandler({ResourceUnauthorizedException.class})
    public ResponseEntity exceptionHandle(ResourceUnauthorizedException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),exception.getMessage()));
    }





}
