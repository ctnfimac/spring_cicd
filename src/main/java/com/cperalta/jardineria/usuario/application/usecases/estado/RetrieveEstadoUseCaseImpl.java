package com.cperalta.jardineria.usuario.application.usecases.estado;

import com.cperalta.jardineria.usuario.domain.ports.in.estado.RetrieveEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.out.EstadoRepositoryPort;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveEstadoUseCaseImpl implements RetrieveEstadoUseCase {

    private final EstadoRepositoryPort estadoRepositoryPort;

    @Override
    public Optional<Estado> getEstadoById(Long id) {
        return estadoRepositoryPort.findById(id);
    }

    @Override
    public List<Estado> getAllEstados() {
        return estadoRepositoryPort.findAll();
    }
}
