package com.microservice.users.servicios.application.services;

import com.microservice.users.servicios.domain.models.Servicio;
import com.microservice.users.servicios.domain.ports.input.servicio.CreateServicioUseCase;
import com.microservice.users.servicios.domain.ports.input.servicio.DeleteServicioUseCase;
import com.microservice.users.servicios.domain.ports.input.servicio.RetrieveServicioUseCase;
import com.microservice.users.servicios.domain.ports.input.servicio.UpdateServicioUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class ServicioService implements RetrieveServicioUseCase, CreateServicioUseCase,
        UpdateServicioUseCase, DeleteServicioUseCase {

    private final RetrieveServicioUseCase retrieveServicioUseCase;
    private final CreateServicioUseCase createServicioUseCase;
    private final UpdateServicioUseCase updateServicioUseCase;
    private final DeleteServicioUseCase deleteServicioUseCase;

    @Override
    public Optional<Servicio> getById(UUID id) {
        return retrieveServicioUseCase.getById(id);
    }

    @Override
    public List<Servicio> getAll() {
        return retrieveServicioUseCase.getAll();
    }

    @Override
    public Servicio create(Servicio servicio) {
        return createServicioUseCase.create(servicio);
    }

    @Override
    public boolean delete(UUID id) {
        return deleteServicioUseCase.delete(id);
    }

    @Override
    public Optional<Servicio> update(UUID id, Servicio servicio) {
        return updateServicioUseCase.update(id, servicio);
    }
}
