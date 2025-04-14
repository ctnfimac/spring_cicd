package com.microservice.users.contratacion.application.usecases.trabajorealizado;

import com.microservice.users.contratacion.domain.models.TrabajoRealizado;
import com.microservice.users.contratacion.domain.ports.input.trabajorealizado.CreateTrabajoRealizadoUseCase;
import com.microservice.users.contratacion.domain.ports.output.TrabajoRealizadoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTrabajoRealizadoUseCaseImpl implements CreateTrabajoRealizadoUseCase {

    private final TrabajoRealizadoRepositoryPort trabajoRealizadoRepositoryPort;

    @Override
    public TrabajoRealizado create(TrabajoRealizado trabajoRealizado) {
        return trabajoRealizadoRepositoryPort.create(trabajoRealizado);
    }
}
