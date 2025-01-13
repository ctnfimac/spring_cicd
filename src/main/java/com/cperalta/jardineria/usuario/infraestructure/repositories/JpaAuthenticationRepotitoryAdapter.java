package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.ports.output.AuthenticationRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.mapper.PersonaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class JpaAuthenticationRepotitoryAdapter implements AuthenticationRepositoryPort {

    private final JpaAuthenticationRepository jpaAuthenticationRepository;
    private final PersonaMapper personaMapper;

    @Override
    public Optional<Persona> login(String email, String contrasenia) {
        return  jpaAuthenticationRepository.findPersonaEntityByEmailAndContrasenia(email, contrasenia).map(personaMapper::personaEntityToPersona);

    }
}
