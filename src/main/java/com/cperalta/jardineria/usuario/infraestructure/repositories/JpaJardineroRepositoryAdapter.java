package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import com.cperalta.jardineria.usuario.domain.ports.output.JardineroRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Optional<Jardinero> getById(Long id) {
        return jpaJardineroRepository.findById(id).map(jardineroMapper::jardineroEntityToJardinero);
    }

    @Override
    public List<Jardinero> getAll() {
        return jpaJardineroRepository.findAll().stream()
                .map(jardineroMapper::jardineroEntityToJardinero)
                .collect(Collectors.toList());
    }

    @Override
    public Jardinero create(Jardinero jardinero) {
        JardineroEntity jardineroEntity = jardineroMapper.jardineroToJardineroEntity(jardinero);
        JardineroEntity jardineroEntityCreado = jpaJardineroRepository.save(jardineroEntity);
        return jardineroMapper.jardineroEntityToJardinero(jardineroEntityCreado);
    }

    @Override
    public Jardinero update(Long id, Jardinero jardinero) {

        return null;
    }

    @Override
    public boolean delete(Long id) {
        if(jpaJardineroRepository.existsById(id)){
            jpaJardineroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
