package com.cperalta.jardineria.usuario.application.usecases.estado;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.ports.in.estado.UpdateEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.out.EstadoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateEstadoUseCaseImpl implements UpdateEstadoUseCase {
    private final EstadoRepositoryPort estadoRepositoryPort;

    @Override
    public Optional<Estado> updateEstado(Long id, Estado estado) {
        return estadoRepositoryPort.update(id, estado);
    }
}
