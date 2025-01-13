package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.ports.input.persona.PersonaRetrieveUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.PersonaRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
public class PersonaService implements PersonaRetrieveUseCase {
    private final PersonaRetrieveUseCase personaRetrieveUseCase;

    @Override
    public Optional<Persona> findByEmail(String email) {
        return personaRetrieveUseCase.findByEmail(email);
    }
}
