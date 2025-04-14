package com.microservice.users.contratacion.domain.ports.input.trabajorealizado;

import com.microservice.users.contratacion.domain.models.TrabajoRealizado;

import java.util.Optional;

public interface UpdateTrabajoRealizadoUseCase {
    Optional<TrabajoRealizado> update(Long id, TrabajoRealizado trabajoRealizado);
}
