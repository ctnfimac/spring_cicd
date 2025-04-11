package com.cperalta.jardineria.usuario.application.usecases.status;

import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.domain.ports.input.status.UpdateStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.StatusRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateStatusUseCaseImpl implements UpdateStatusUseCase {
    private final StatusRepositoryPort statusRepositoryPort;

    @Override
    public Optional<Status> update(Long id, Status status) {
        return statusRepositoryPort.update(id, status);
    }
}
