package com.cperalta.jardineria.contratacion.application.usecases.estadocontratacion;

import com.cperalta.jardineria.contratacion.domain.models.EstadoContratacion;
import com.cperalta.jardineria.contratacion.domain.ports.input.estadocontratacion.UpdateEstadoContratacionUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.EstadoContratacionRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateEstadoContratacionUseCaseImpl implements UpdateEstadoContratacionUseCase {

    private final EstadoContratacionRepositoryPort estadoContratacionRepositoryPort;

    @Override
    public Optional<EstadoContratacion> update(Long id, EstadoContratacion estadoContratacion) {
        return estadoContratacionRepositoryPort.update(id, estadoContratacion);
    }
}
