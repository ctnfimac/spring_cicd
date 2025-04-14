package com.microservice.users.application.usecases.status;

import com.microservice.users.domain.ports.input.status.RetrieveStatusUseCase;
import com.microservice.users.domain.ports.output.StatusRepositoryPort;
import com.microservice.users.domain.models.Status;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveStatusUseCaseImpl implements RetrieveStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;

    @Override
    public Optional<Status> getById(Long id) {
        return statusRepositoryPort.findById(id);
    }

    @Override
    public List<Status> getAll() {
        return statusRepositoryPort.findAll();
    }
}
