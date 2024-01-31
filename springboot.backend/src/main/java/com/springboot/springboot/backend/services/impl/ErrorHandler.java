package com.springboot.springboot.backend.services.impl;

import com.springboot.springboot.backend.exception.ErrorMessage;
import com.springboot.springboot.backend.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorMessage error =new ErrorMessage();
        error.setCode(101);
        error.setMessage(exception.getMessage());
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
