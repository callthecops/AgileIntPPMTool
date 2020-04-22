package com.example.PPMTool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// An exception will have 2 components.First component is the exception response and the second compoenent
// is the exception itself.This is the exception itself.Because of this we have to annotate it with @ResponseStatus
//Next we create a constructor wich takes a message as argument
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {
    public ProjectIdException(String message) {
        super(message);
    }
}
