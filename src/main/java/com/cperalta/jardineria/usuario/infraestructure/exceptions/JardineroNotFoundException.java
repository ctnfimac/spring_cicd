package com.cperalta.jardineria.usuario.infraestructure.exceptions;

public class JardineroNotFoundException extends RuntimeException {
    public JardineroNotFoundException(String message) {
        super(message);
    }
}

