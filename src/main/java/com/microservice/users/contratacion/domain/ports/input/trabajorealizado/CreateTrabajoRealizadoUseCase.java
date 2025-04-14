package com.microservice.users.contratacion.domain.ports.input.trabajorealizado;

import com.microservice.users.contratacion.domain.models.TrabajoRealizado;

public interface CreateTrabajoRealizadoUseCase {
    TrabajoRealizado create(TrabajoRealizado trabajoRealizado);
}
