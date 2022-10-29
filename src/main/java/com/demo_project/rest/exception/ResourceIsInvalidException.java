package com.demo_project.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceIsInvalidException extends RuntimeException {
    public ResourceIsInvalidException(String message) {
        super(message);
    }
}
