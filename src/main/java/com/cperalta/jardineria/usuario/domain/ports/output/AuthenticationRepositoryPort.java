package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Persona;

import java.util.Optional;

public interface AuthenticationRepositoryPort {
    Optional<Persona> login(String email, String contrasenia);
}
