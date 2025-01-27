package com.cperalta.jardineria.usuario.infraestructure.exceptions;

public class RolNotFoundException extends RuntimeException {
    public RolNotFoundException(String message) {
        super(message);
    }
}