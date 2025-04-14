package com.microservice.users.contratacion.domain.ports.output;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;

import java.util.List;
import java.util.Optional;

public interface EstadoContratacionRepositoryPort {
    List<EstadoContratacion> getAll();
    Optional<EstadoContratacion> getById(Long id);
    EstadoContratacion create(EstadoContratacion estadoContratacion);
    Optional<EstadoContratacion> update(Long id, EstadoContratacion estadoContratacion);
    boolean delete(Long id);
}
