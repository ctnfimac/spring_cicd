package com.cperalta.jardineria.usuario.application.usecases.estado;

import com.cperalta.jardineria.usuario.domain.ports.in.estado.DeleteEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.out.EstadoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteEstadoUseCaseImpl implements DeleteEstadoUseCase {

    private final EstadoRepositoryPort estadoRepositoryPort;

    @Override
    public boolean deleteEstado(Long id) {
        return estadoRepositoryPort.delete(id);
    }
}
