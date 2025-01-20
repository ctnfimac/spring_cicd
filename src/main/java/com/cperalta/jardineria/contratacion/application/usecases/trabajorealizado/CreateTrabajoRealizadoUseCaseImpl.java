package com.cperalta.jardineria.contratacion.application.usecases.trabajorealizado;

import com.cperalta.jardineria.contratacion.domain.models.TrabajoRealizado;
import com.cperalta.jardineria.contratacion.domain.ports.input.trabajorealizado.CreateTrabajoRealizadoUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.TrabajoRealizadoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTrabajoRealizadoUseCaseImpl implements CreateTrabajoRealizadoUseCase {

    private final TrabajoRealizadoRepositoryPort trabajoRealizadoRepositoryPort;

    @Override
    public TrabajoRealizado create(TrabajoRealizado trabajoRealizado) {
        return trabajoRealizadoRepositoryPort.create(trabajoRealizado);
    }
}
