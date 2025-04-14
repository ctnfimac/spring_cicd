package com.microservice.users.application.usecases.status;

import com.microservice.users.domain.ports.input.status.DeleteStatusUseCase;
import com.microservice.users.domain.ports.output.StatusRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteStatusUseCaseImpl implements DeleteStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return statusRepositoryPort.delete(id);
    }
}
