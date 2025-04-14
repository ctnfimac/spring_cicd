package com.microservice.users.contratacion.application.services;

import com.microservice.users.contratacion.domain.models.TrabajoRealizado;
import com.microservice.users.contratacion.domain.ports.input.trabajorealizado.CreateTrabajoRealizadoUseCase;
import com.microservice.users.contratacion.domain.ports.input.trabajorealizado.DeleteTrabajoRealizadoUseCase;
import com.microservice.users.contratacion.domain.ports.input.trabajorealizado.RetrieveTrabajoRealizadoUseCase;
import com.microservice.users.contratacion.domain.ports.input.trabajorealizado.UpdateTrabajoRealizadoUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TrabajoRealizadoService implements RetrieveTrabajoRealizadoUseCase, CreateTrabajoRealizadoUseCase,
        UpdateTrabajoRealizadoUseCase, DeleteTrabajoRealizadoUseCase {

    private final RetrieveTrabajoRealizadoUseCase retrieveTrabajoRealizadoUseCase;
    private final CreateTrabajoRealizadoUseCase createTrabajoRealizadoUseCase;
    private final UpdateTrabajoRealizadoUseCase updateTrabajoRealizadoUseCase;
    private final DeleteTrabajoRealizadoUseCase deleteTrabajoRealizadoUseCase;

    @Override
    public TrabajoRealizado create(TrabajoRealizado trabajoRealizado) {
        return createTrabajoRealizadoUseCase.create(trabajoRealizado);
    }

    @Override
    public boolean delete(Long id) {
        return deleteTrabajoRealizadoUseCase.delete(id);
    }

    @Override
    public List<TrabajoRealizado> getAll() {
        return retrieveTrabajoRealizadoUseCase.getAll();
    }

    @Override
    public Optional<TrabajoRealizado> getById(Long id) {
        return retrieveTrabajoRealizadoUseCase.getById(id);
    }

    @Override
    public Optional<TrabajoRealizado> update(Long id, TrabajoRealizado trabajoRealizado) {
        return updateTrabajoRealizadoUseCase.update(id, trabajoRealizado);
    }
}
