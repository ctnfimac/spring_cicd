package com.cperalta.jardineria.usuario.application.usecases.estado;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.ports.input.estado.CreateEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.EstadoRepositoryPort;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CreateEstadoUseCaseImpl implements CreateEstadoUseCase {

    private final EstadoRepositoryPort estadoRepositoryPort;

    @Override
    public Estado createEstado(Estado estado) {
        return estadoRepositoryPort.create(estado);
    }
}
