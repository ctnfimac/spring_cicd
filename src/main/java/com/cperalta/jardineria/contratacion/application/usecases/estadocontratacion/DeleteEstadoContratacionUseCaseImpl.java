package com.cperalta.jardineria.contratacion.application.usecases.estadocontratacion;

import com.cperalta.jardineria.contratacion.domain.ports.input.estadocontratacion.DeleteEstadoContratacionUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.EstadoContratacionRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteEstadoContratacionUseCaseImpl implements DeleteEstadoContratacionUseCase {

    private final EstadoContratacionRepositoryPort estadoContratacionRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return estadoContratacionRepositoryPort.delete(id);
    }
}
