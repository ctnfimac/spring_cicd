package com.microservice.users.servicios.application.usecases.tipodeservicio;

import com.microservice.users.servicios.domain.models.TipoDeServicio;
import com.microservice.users.servicios.domain.ports.input.tipoDeServicio.RetrieveTipoDeServicioUseCase;
import com.microservice.users.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveTipoDeServicioUseCaseImpl implements RetrieveTipoDeServicioUseCase {

    private final TipoDeServicioRepositoryPort tipoDeServicioRepositoryPort;

    @Override
    public List<TipoDeServicio> getAll() {
        return tipoDeServicioRepositoryPort.getAll();
    }

    @Override
    public Optional<TipoDeServicio> getById(Long id) {
        return tipoDeServicioRepositoryPort.getById(id);
    }

}
