package com.microservice.users.contratacion.infraestructure.repositories;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;
import com.microservice.users.contratacion.domain.ports.output.EstadoContratacionRepositoryPort;
import com.microservice.users.contratacion.infraestructure.entities.EstadoContratacionEntity;
import com.microservice.users.contratacion.infraestructure.mapper.EstadoContratacionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaEstadoContratacionRepositoryAdapter implements EstadoContratacionRepositoryPort {

    private final JpaEstadoContratacionRespository jpaEstadoContratacionRespository;
    private final EstadoContratacionMapper estadoContratacionMapper;

    @Override
    public List<EstadoContratacion> getAll() {
        return jpaEstadoContratacionRespository.findAll().stream()
                .map(estadoContratacionMapper::estadoContratacionEntityToEstadoContratacion)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EstadoContratacion> getById(Long id) {
        return jpaEstadoContratacionRespository.findById(id).map(estadoContratacionMapper::estadoContratacionEntityToEstadoContratacion);
    }

    @Override
    public EstadoContratacion create(EstadoContratacion estadoContratacion) {
        EstadoContratacionEntity estadoContratacionEntity = estadoContratacionMapper.estadoContratacionToEstadoContratacionEntity(estadoContratacion);
        EstadoContratacionEntity estadoContratacionEntityNuevo = jpaEstadoContratacionRespository.save(estadoContratacionEntity);
        return estadoContratacionMapper.estadoContratacionEntityToEstadoContratacion(estadoContratacionEntityNuevo);
    }

    @Override
    public Optional<EstadoContratacion> update(Long id, EstadoContratacion estadoContratacion) {
        if(jpaEstadoContratacionRespository.existsById(id)){
            EstadoContratacionEntity estadoContratacionEntityActual = jpaEstadoContratacionRespository.findById(id).get();

            estadoContratacionEntityActual.setDescripcion( estadoContratacion.getDescripcion() != null ?
                estadoContratacion.getDescripcion() : estadoContratacionEntityActual.getDescripcion()
            );

            EstadoContratacionEntity estadoContratacionEntityActualizado = jpaEstadoContratacionRespository.save(estadoContratacionEntityActual);
            return Optional.ofNullable(estadoContratacionMapper.estadoContratacionEntityToEstadoContratacion(estadoContratacionEntityActualizado));

        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        if(jpaEstadoContratacionRespository.existsById(id)){
            jpaEstadoContratacionRespository.deleteById(id);
            return true;
        }
        return false;
    }
}
