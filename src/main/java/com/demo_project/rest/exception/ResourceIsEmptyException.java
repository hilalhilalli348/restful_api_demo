package com.demo_project.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ResourceIsEmptyException extends RuntimeException{
    public ResourceIsEmptyException(String message){
        super(message);
    }
}
