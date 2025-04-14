package com.microservice.users.contratacion.domain.ports.input.estadocontratacion;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;

import java.util.Optional;

public interface UpdateEstadoContratacionUseCase {
    Optional<EstadoContratacion> update(Long id, EstadoContratacion estadoContratacion);
}
