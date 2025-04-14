package com.microservice.users.infraestructure.exceptions;

public class JardineroNotFoundException extends RuntimeException {
    public JardineroNotFoundException(String message) {
        super(message);
    }
}

