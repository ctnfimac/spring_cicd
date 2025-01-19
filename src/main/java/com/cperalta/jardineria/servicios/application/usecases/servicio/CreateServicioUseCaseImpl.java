package com.cperalta.jardineria.servicios.application.usecases.servicio;

import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.domain.ports.input.servicio.CreateServicioUseCase;
import com.cperalta.jardineria.servicios.domain.ports.output.ServicioRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateServicioUseCaseImpl implements CreateServicioUseCase {
    private final ServicioRepositoryPort servicioRepositoryPort;

    public Servicio create(Servicio servicio){
        return servicioRepositoryPort.create(servicio);
    }

}
