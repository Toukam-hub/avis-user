package com.chat.avisutilisateur.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Model> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(getModel(ex.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Model> handleResourceAlreadyExistException(ResourceAlreadyExistException ex) {
        return new ResponseEntity<>(getModel(ex.getMessage(),HttpStatus.CONFLICT.value(),Instant.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceExpriredException.class)
    public ResponseEntity<Model> handleResourceExpriredException(ResourceExpriredException ex) {
        return new ResponseEntity<>(getModel(ex.getMessage(),HttpStatus.BAD_REQUEST.value() ,Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Model> handleIllegalArgumentException(IllegalArgumentException ex) {

        return new ResponseEntity<>(getModel(ex.getMessage(), HttpStatus.BAD_REQUEST.value(),Instant.now()), HttpStatus.BAD_REQUEST);
    }

    private Model getModel(String message, int httpStatus,Instant date){
        return Model.builder()
                .message(message)
                .statusCode(httpStatus)
                .date(date)
                .build();
    }

}