package com.microservice.users.servicios.application.usecases.tipodeservicio;

import com.microservice.users.servicios.domain.models.TipoDeServicio;
import com.microservice.users.servicios.domain.ports.input.tipoDeServicio.CreateTipoDeServicioUseCase;
import com.microservice.users.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTipoDeServicioUseCaseImpl implements CreateTipoDeServicioUseCase {
    private final TipoDeServicioRepositoryPort tipoDeServicioRepositoryPort;

    public TipoDeServicio create(TipoDeServicio tipoDeServicio){
        return tipoDeServicioRepositoryPort.create(tipoDeServicio);
    }
}
