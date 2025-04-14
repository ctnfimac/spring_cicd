package com.microservice.users.servicios.application.usecases.servicio;

import com.microservice.users.servicios.domain.models.Servicio;
import com.microservice.users.servicios.domain.ports.input.servicio.UpdateServicioUseCase;
import com.microservice.users.servicios.domain.ports.output.ServicioRepositoryPort;
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
