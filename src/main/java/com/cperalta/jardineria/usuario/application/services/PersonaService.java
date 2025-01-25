package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.ports.input.persona.PersonaRetrieveUseCase;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PersonaService implements PersonaRetrieveUseCase {
    private final PersonaRetrieveUseCase personaRetrieveUseCase;

    @Override
    public Optional<Persona> findByEmail(String email) {
        return personaRetrieveUseCase.findByEmail(email);
    }
}
