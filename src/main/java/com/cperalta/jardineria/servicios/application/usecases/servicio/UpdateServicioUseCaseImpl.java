package com.cperalta.jardineria.servicios.application.usecases.servicio;

import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.domain.ports.input.servicio.UpdateServicioUseCase;
import com.cperalta.jardineria.servicios.domain.ports.output.ServicioRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class UpdateServicioUseCaseImpl implements UpdateServicioUseCase {
    private final ServicioRepositoryPort servicioRepositoryPort;

    public Optional<Servicio> update(UUID id, Servicio servicio){
        return servicioRepositoryPort.update(id, servicio);
    }
}
