package com.study.goal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException( ResourceNotFoundException ex ){
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Carro não localizado", ex.getMessage());
        return new ResponseEntity<>( error, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException( Exception ex ){
        ErrorResponse error = new ErrorResponse( LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro" ,ex.getMessage() );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
