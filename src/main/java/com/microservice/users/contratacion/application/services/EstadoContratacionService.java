package com.microservice.users.contratacion.application.services;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;
import com.microservice.users.contratacion.domain.ports.input.estadocontratacion.CreateEstadoContratacionUseCase;
import com.microservice.users.contratacion.domain.ports.input.estadocontratacion.DeleteEstadoContratacionUseCase;
import com.microservice.users.contratacion.domain.ports.input.estadocontratacion.RetrieveEstadoContratacionUseCase;
import com.microservice.users.contratacion.domain.ports.input.estadocontratacion.UpdateEstadoContratacionUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class EstadoContratacionService implements RetrieveEstadoContratacionUseCase, CreateEstadoContratacionUseCase,
        UpdateEstadoContratacionUseCase, DeleteEstadoContratacionUseCase {

    private final RetrieveEstadoContratacionUseCase retrieveEstadoContratacionUseCase;
    private final CreateEstadoContratacionUseCase createEstadoContratacionUseCase;
    private final UpdateEstadoContratacionUseCase updateEstadoContratacionUseCase;
    private final DeleteEstadoContratacionUseCase deleteEstadoContratacionUseCase;

    @Override
    public EstadoContratacion create(EstadoContratacion estadoContratacion) {
        return createEstadoContratacionUseCase.create(estadoContratacion);
    }

    @Override
    public boolean delete(Long id) {
        return deleteEstadoContratacionUseCase.delete(id);
    }

    @Override
    public List<EstadoContratacion> getAll() {
        return retrieveEstadoContratacionUseCase.getAll();
    }

    @Override
    public Optional<EstadoContratacion> getById(Long id) {
        return retrieveEstadoContratacionUseCase.getById(id);
    }

    @Override
    public Optional<EstadoContratacion> update(Long id, EstadoContratacion estadoContratacion) {
        return updateEstadoContratacionUseCase.update(id, estadoContratacion);
    }
}
