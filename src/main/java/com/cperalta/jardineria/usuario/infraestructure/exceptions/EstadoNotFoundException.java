package com.cperalta.jardineria.usuario.infraestructure.exceptions;

public class EstadoNotFoundException extends RuntimeException {
    public EstadoNotFoundException(String message) {
        super(message);
    }
}