package com.cperalta.jardineria.usuario.application.usecases.persona;

import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.ports.input.persona.PersonaRetrieveUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.PersonaRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PersonaRetrieveUseCaseImpl implements PersonaRetrieveUseCase {

    private final PersonaRepositoryPort personaRepositoryPort;

    @Override
    public Optional<Persona> findByEmail(String email) {
        return personaRepositoryPort.findByEmail(email);
    }
}
