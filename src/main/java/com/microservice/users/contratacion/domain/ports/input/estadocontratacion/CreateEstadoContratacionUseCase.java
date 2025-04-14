package com.microservice.users.contratacion.domain.ports.input.estadocontratacion;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;

public interface CreateEstadoContratacionUseCase {
    EstadoContratacion create(EstadoContratacion estadoContratacion);
}
