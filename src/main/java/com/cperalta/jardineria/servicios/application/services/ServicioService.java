package com.cperalta.jardineria.servicios.application.services;

import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.domain.ports.input.servicio.RetrieveServicioUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class ServicioService implements RetrieveServicioUseCase {

    private final RetrieveServicioUseCase retrieveServicioUseCase;

    @Override
    public Optional<Servicio> getById(UUID id) {
        return retrieveServicioUseCase.getById(id);
    }

    @Override
    public List<Servicio> getAll() {
        return retrieveServicioUseCase.getAll();
    }
}
