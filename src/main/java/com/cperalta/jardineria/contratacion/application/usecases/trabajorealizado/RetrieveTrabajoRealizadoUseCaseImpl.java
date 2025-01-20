package com.cperalta.jardineria.contratacion.application.usecases.trabajorealizado;

import com.cperalta.jardineria.contratacion.domain.models.TrabajoRealizado;
import com.cperalta.jardineria.contratacion.domain.ports.input.trabajorealizado.RetrieveTrabajoRealizadoUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.TrabajoRealizadoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveTrabajoRealizadoUseCaseImpl implements RetrieveTrabajoRealizadoUseCase {

    private final TrabajoRealizadoRepositoryPort trabajoRealizadoRepositoryPort;

    @Override
    public List<TrabajoRealizado> getAll() {
        return trabajoRealizadoRepositoryPort.getAll();
    }

    @Override
    public Optional<TrabajoRealizado> getById(Long id) {
        return trabajoRealizadoRepositoryPort.getById(id);
    }
}
