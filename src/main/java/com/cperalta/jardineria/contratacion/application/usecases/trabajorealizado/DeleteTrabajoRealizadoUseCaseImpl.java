package com.cperalta.jardineria.contratacion.application.usecases.trabajorealizado;

import com.cperalta.jardineria.contratacion.domain.ports.input.trabajorealizado.DeleteTrabajoRealizadoUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.TrabajoRealizadoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTrabajoRealizadoUseCaseImpl implements DeleteTrabajoRealizadoUseCase {

    private final TrabajoRealizadoRepositoryPort trabajoRealizadoRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return trabajoRealizadoRepositoryPort.delete(id);
    }
}
