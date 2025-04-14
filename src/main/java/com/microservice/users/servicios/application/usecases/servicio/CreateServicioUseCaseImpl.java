package com.microservice.users.servicios.application.usecases.servicio;

import com.microservice.users.servicios.domain.models.Servicio;
import com.microservice.users.servicios.domain.ports.input.servicio.CreateServicioUseCase;
import com.microservice.users.servicios.domain.ports.output.ServicioRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateServicioUseCaseImpl implements CreateServicioUseCase {
    private final ServicioRepositoryPort servicioRepositoryPort;

    public Servicio create(Servicio servicio){
        return servicioRepositoryPort.create(servicio);
    }

}
