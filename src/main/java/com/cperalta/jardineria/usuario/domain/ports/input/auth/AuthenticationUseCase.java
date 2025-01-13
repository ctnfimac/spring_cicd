package com.cperalta.jardineria.usuario.domain.ports.input.auth;

import com.cperalta.jardineria.usuario.domain.models.Persona;

import java.util.Optional;

public interface AuthenticationUseCase {
    Optional<Persona> login(String email, String password);
}
