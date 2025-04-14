package com.microservice.users.servicios.application.usecases.tipodeservicio;

import com.microservice.users.servicios.domain.models.TipoDeServicio;
import com.microservice.users.servicios.domain.ports.input.tipoDeServicio.UpdateTipoDeServicioUseCase;
import com.microservice.users.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateTipoDeServicioUseCaseImpl implements UpdateTipoDeServicioUseCase {

    private final TipoDeServicioRepositoryPort tipoDeServicioRepositoryPort;

    @Override
    public Optional<TipoDeServicio> update(Long id, TipoDeServicio tipoDeServicio) {
        return tipoDeServicioRepositoryPort.update(id, tipoDeServicio);
    }
}
