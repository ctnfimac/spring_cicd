package com.microservice.users.infraestructure.exceptions;

public class EstadoNotFoundException extends RuntimeException {
    public EstadoNotFoundException(String message) {
        super(message);
    }
}