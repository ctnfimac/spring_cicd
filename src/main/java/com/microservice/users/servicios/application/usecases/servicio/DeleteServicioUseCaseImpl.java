package com.microservice.users.servicios.application.usecases.servicio;

import com.microservice.users.servicios.domain.ports.input.servicio.DeleteServicioUseCase;
import com.microservice.users.servicios.domain.ports.output.ServicioRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class DeleteServicioUseCaseImpl implements DeleteServicioUseCase {
    private final ServicioRepositoryPort servicioRepositoryPort;

    public boolean delete(UUID id){
        return servicioRepositoryPort.delete(id);
    }
}
