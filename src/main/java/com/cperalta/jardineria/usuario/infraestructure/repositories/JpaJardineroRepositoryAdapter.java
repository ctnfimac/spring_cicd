package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import com.cperalta.jardineria.usuario.domain.ports.output.JardineroRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class JpaJardineroRepositoryAdapter implements JardineroRepositoryPort {

    private final JpaJardineroRepository jpaJardineroRepository;
    private final JardineroMapper jardineroMapper;


    @Override
    public Optional<Jardinero> findByEmail(String email) {
        return jpaJardineroRepository.findJardineroEntityByPersonaEmail(email).map(jardineroMapper::jardineroEntityToJardinero);
    }

    @Override
    public Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia) {
        return jpaJardineroRepository.findJardineroEntityByPersonaEmailAndPersonaContrasenia(email,contrasenia).map(jardineroMapper::jardineroEntityToJardinero);
    }
}
