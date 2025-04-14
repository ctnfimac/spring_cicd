package com.microservice.users.contratacion.application.usecases.estadocontratacion;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;
import com.microservice.users.contratacion.domain.ports.input.estadocontratacion.CreateEstadoContratacionUseCase;
import com.microservice.users.contratacion.domain.ports.output.EstadoContratacionRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateEstadoContratacionUseCaseImpl implements CreateEstadoContratacionUseCase {

    private final EstadoContratacionRepositoryPort estadoContratacionRepositoryPort;

    @Override
    public EstadoContratacion create(EstadoContratacion estadoContratacion) {
        return estadoContratacionRepositoryPort.create(estadoContratacion);
    }
}
