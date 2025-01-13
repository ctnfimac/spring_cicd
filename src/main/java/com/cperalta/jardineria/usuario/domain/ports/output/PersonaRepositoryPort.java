package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Persona;

import java.util.Optional;

public interface PersonaRepositoryPort {
    Optional<Persona> findByEmail(String email);
}
