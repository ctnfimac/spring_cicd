package com.cperalta.jardineria.usuario.infraestructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, String>> respuestaTemplate(String msg, HttpStatus httpStatus){
        return new ResponseEntity<>(
                Map.of("error", msg),
                httpStatus
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of("error", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateResourceException(DuplicateResourceException ex) {
        return respuestaTemplate(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EstadoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEstadoNotFoundException(EstadoNotFoundException ex) {
        return respuestaTemplate(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RolNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRolNotFoundException(RolNotFoundException ex) {
        return respuestaTemplate(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



}