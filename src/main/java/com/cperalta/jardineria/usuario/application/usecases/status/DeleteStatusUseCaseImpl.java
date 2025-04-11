package com.cperalta.jardineria.usuario.application.usecases.status;

import com.cperalta.jardineria.usuario.domain.ports.input.status.DeleteStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.StatusRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteStatusUseCaseImpl implements DeleteStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return statusRepositoryPort.delete(id);
    }
}
