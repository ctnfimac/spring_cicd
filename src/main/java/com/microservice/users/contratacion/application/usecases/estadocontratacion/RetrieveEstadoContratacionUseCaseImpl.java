package com.microservice.users.contratacion.application.usecases.estadocontratacion;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;
import com.microservice.users.contratacion.domain.ports.input.estadocontratacion.RetrieveEstadoContratacionUseCase;
import com.microservice.users.contratacion.domain.ports.output.EstadoContratacionRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveEstadoContratacionUseCaseImpl implements RetrieveEstadoContratacionUseCase {

    private final EstadoContratacionRepositoryPort estadoContratacionRepositoryPort;

    @Override
    public List<EstadoContratacion> getAll() {
        return estadoContratacionRepositoryPort.getAll();
    }

    @Override
    public Optional<EstadoContratacion> getById(Long id) {
        return estadoContratacionRepositoryPort.getById(id);
    }
}
