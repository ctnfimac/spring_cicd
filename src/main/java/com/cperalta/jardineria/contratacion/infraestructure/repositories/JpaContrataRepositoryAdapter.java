package com.cperalta.jardineria.contratacion.infraestructure.repositories;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;
import com.cperalta.jardineria.contratacion.domain.ports.output.ContrataRepositoryPort;
import com.cperalta.jardineria.contratacion.infraestructure.mapper.ContrataMapper;
import com.cperalta.jardineria.usuario.infraestructure.repositories.JpaJardineroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaContrataRepositoryAdapter implements ContrataRepositoryPort {

    private final JpaContrataRepository jpaContrataRepository;
    //private final JpaJardineroRepository jpaJardineroRepository;
    private final ContrataMapper contrataMapper;

    @Override
    public Optional<Contrata> getById(Long id) {
        return jpaContrataRepository.findById(id).map(contrataMapper::contrataEntityToContrata);
    }

    @Override
    public List<Contrata> getAll() {
        return jpaContrataRepository.findAll().stream()
                .map(contrataMapper::contrataEntityToContrata)
                .collect(Collectors.toList());
    }

    @Override
    public Contrata create(Contrata contrata) {
        return null;
    }

    @Override
    public Optional<Contrata> update(Long id, Contrata contrata) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
