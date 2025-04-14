package com.microservice.users.servicios.application.services;

import com.microservice.users.servicios.domain.models.TipoDeServicio;
import com.microservice.users.servicios.domain.ports.input.tipoDeServicio.CreateTipoDeServicioUseCase;
import com.microservice.users.servicios.domain.ports.input.tipoDeServicio.DeleteTipoDeServicioUseCase;
import com.microservice.users.servicios.domain.ports.input.tipoDeServicio.RetrieveTipoDeServicioUseCase;
import com.microservice.users.servicios.domain.ports.input.tipoDeServicio.UpdateTipoDeServicioUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TipoDeServicioService implements RetrieveTipoDeServicioUseCase, CreateTipoDeServicioUseCase,
        UpdateTipoDeServicioUseCase, DeleteTipoDeServicioUseCase {

    private final RetrieveTipoDeServicioUseCase retrieveTipoDeServicioUseCase;
    private final CreateTipoDeServicioUseCase createTipoDeServicioUseCase;
    private final UpdateTipoDeServicioUseCase updateTipoDeServicioUseCase;
    private final DeleteTipoDeServicioUseCase deleteTipoDeServicioUseCase;

    @Override
    public List<TipoDeServicio> getAll() {
        return retrieveTipoDeServicioUseCase.getAll();
    }

    @Override
    public Optional<TipoDeServicio> getById(Long id) {
        return retrieveTipoDeServicioUseCase.getById(id);
    }

    @Override
    public TipoDeServicio create(TipoDeServicio tipoDeServicio) {
        return createTipoDeServicioUseCase.create(tipoDeServicio);
    }

    @Override
    public boolean delete(Long id) {
        return deleteTipoDeServicioUseCase.delete(id);
    }

    @Override
    public Optional<TipoDeServicio> update(Long id, TipoDeServicio tipoDeServicio) {
        return updateTipoDeServicioUseCase.update(id, tipoDeServicio);
    }
}
