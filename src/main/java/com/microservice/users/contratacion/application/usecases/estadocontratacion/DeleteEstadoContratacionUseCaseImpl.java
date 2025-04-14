package com.microservice.users.contratacion.application.usecases.estadocontratacion;

import com.microservice.users.contratacion.domain.ports.input.estadocontratacion.DeleteEstadoContratacionUseCase;
import com.microservice.users.contratacion.domain.ports.output.EstadoContratacionRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteEstadoContratacionUseCaseImpl implements DeleteEstadoContratacionUseCase {

    private final EstadoContratacionRepositoryPort estadoContratacionRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return estadoContratacionRepositoryPort.delete(id);
    }
}
