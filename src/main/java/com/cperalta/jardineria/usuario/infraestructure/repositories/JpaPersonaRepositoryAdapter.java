package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.ports.output.PersonaRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.mapper.PersonaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class JpaPersonaRepositoryAdapter implements PersonaRepositoryPort {

    private final JpaPersonaRepository jpaPersonaRepository;
    private final PersonaMapper personaMapper;

    @Override
    public Optional<Persona> findByEmail(String email) {
        return jpaPersonaRepository.findByEmail(email).map(personaMapper::personaEntityToPersona);
    }
}
