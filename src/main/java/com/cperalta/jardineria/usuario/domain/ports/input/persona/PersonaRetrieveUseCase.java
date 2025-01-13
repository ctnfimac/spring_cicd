package com.cperalta.jardineria.usuario.domain.ports.input.persona;

import com.cperalta.jardineria.usuario.domain.models.Persona;

import java.util.Optional;

public interface PersonaRetrieveUseCase {
    Optional<Persona> findByEmail(String email);
}
