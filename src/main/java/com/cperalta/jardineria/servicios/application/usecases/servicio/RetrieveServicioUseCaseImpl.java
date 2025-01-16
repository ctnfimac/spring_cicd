package com.cperalta.jardineria.servicios.application.usecases.servicio;

import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.domain.ports.input.servicio.RetrieveServicioUseCase;
import com.cperalta.jardineria.servicios.domain.ports.output.ServicioRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class RetrieveServicioUseCaseImpl implements RetrieveServicioUseCase {

    private final ServicioRepositoryPort servicioRepositoryPort;

    @Override
    public Optional<Servicio> getById(UUID id) {
        return servicioRepositoryPort.getById(id);
    }

    @Override
    public List<Servicio> getAll() {
        return servicioRepositoryPort.getAll();
    }
}
