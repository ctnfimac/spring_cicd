package com.cperalta.jardineria.contratacion.application.usecases.estadocontratacion;

import com.cperalta.jardineria.contratacion.domain.models.EstadoContratacion;
import com.cperalta.jardineria.contratacion.domain.ports.input.estadocontratacion.CreateEstadoContratacionUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.EstadoContratacionRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateEstadoContratacionUseCaseImpl implements CreateEstadoContratacionUseCase {

    private final EstadoContratacionRepositoryPort estadoContratacionRepositoryPort;

    @Override
    public EstadoContratacion create(EstadoContratacion estadoContratacion) {
        return estadoContratacionRepositoryPort.create(estadoContratacion);
    }
}
