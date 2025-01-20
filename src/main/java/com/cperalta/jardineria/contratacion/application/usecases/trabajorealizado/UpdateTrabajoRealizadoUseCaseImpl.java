package com.cperalta.jardineria.contratacion.application.usecases.trabajorealizado;

import com.cperalta.jardineria.contratacion.domain.models.TrabajoRealizado;
import com.cperalta.jardineria.contratacion.domain.ports.input.trabajorealizado.UpdateTrabajoRealizadoUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.TrabajoRealizadoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateTrabajoRealizadoUseCaseImpl implements UpdateTrabajoRealizadoUseCase {

    private final TrabajoRealizadoRepositoryPort trabajoRealizadoRepositoryPort;

    @Override
    public Optional<TrabajoRealizado> update(Long id, TrabajoRealizado trabajoRealizado) {
        return trabajoRealizadoRepositoryPort.update(id, trabajoRealizado);
    }
}
