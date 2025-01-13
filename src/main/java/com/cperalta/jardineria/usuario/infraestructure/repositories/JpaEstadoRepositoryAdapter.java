package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.ports.output.EstadoRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.entities.EstadoEntity;
import com.cperalta.jardineria.usuario.infraestructure.mapper.EstadoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaEstadoRepositoryAdapter implements EstadoRepositoryPort {

    private final JpaEstadoRepository jpaEstadoRepository;
    private final EstadoMapper estadoMapper;

    @Override
    public Optional<Estado> findById(Long id) {
        return jpaEstadoRepository.findById(id).map(estadoMapper::estadoEntityToEstado);
    }

    @Override
    public List<Estado> findAll() {
        return jpaEstadoRepository.findAll().stream()
                .map(estadoMapper::estadoEntityToEstado)
                .collect(Collectors.toList());
    }

    @Override
    public Estado create(Estado estado) {
        EstadoEntity estadoEntity = estadoMapper.estadoToEstadoEntity(estado);
        EstadoEntity savedEstadoEntity = jpaEstadoRepository.save(estadoEntity);
        return estadoMapper.estadoEntityToEstado(savedEstadoEntity);
    }

    @Override
    public boolean delete(Long id) {
        if(jpaEstadoRepository.existsById(id)) {
            jpaEstadoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Estado> update(Long id, Estado estado) {
        if(jpaEstadoRepository.existsById(id)){
            if (estado.getDescripcion() != null &&
                    jpaEstadoRepository.getEstadoByDescripcion(estado.getDescripcion()).isPresent()) {
                throw new IllegalArgumentException("Ya existe un Estado con la descripción: " + estado.getDescripcion());
            }
            EstadoEntity estadoEntityActual = jpaEstadoRepository.getById(id);
            estadoEntityActual.setDescripcion(estado.getDescripcion());
            EstadoEntity estadoActualizado = jpaEstadoRepository.save(estadoEntityActual);
            return Optional.of(estadoMapper.estadoEntityToEstado(estadoActualizado));
        }
        return Optional.empty();
    }
}
