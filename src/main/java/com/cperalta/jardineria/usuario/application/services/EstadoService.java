package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.ports.in.estado.CreateEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.in.estado.DeleteEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.ports.in.estado.RetrieveEstadoUseCase;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.ports.in.estado.UpdateEstadoUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class EstadoService implements RetrieveEstadoUseCase, CreateEstadoUseCase,
        DeleteEstadoUseCase, UpdateEstadoUseCase {

    private final RetrieveEstadoUseCase retrieveEstadoUseCase;
    private final CreateEstadoUseCase createEstadoUseCase;
    private final DeleteEstadoUseCase deleteEstadoUseCase;
    private final UpdateEstadoUseCase updateEstadoUseCase;

    @Override
    public Optional<Estado> getEstadoById(Long id) {
        return retrieveEstadoUseCase.getEstadoById(id);
    }

    @Override
    public List<Estado> getAllEstados() {
        return retrieveEstadoUseCase.getAllEstados();
    }

    @Override
    public Estado createEstado(Estado estado) {
        return createEstadoUseCase.createEstado(estado);
    }

    @Override
    public boolean deleteEstado(Long id) {
        return deleteEstadoUseCase.deleteEstado(id);
    }

    @Override
    public Optional<Estado> updateEstado(Long id, Estado estado) {
        return updateEstadoUseCase.updateEstado(id, estado);
    }
}
