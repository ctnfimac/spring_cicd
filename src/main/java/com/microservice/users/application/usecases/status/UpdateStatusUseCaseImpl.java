package com.microservice.users.application.usecases.status;

import com.microservice.users.domain.models.Status;
import com.microservice.users.domain.ports.input.status.UpdateStatusUseCase;
import com.microservice.users.domain.ports.output.StatusRepositoryPort;
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
