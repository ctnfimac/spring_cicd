package com.microservice.users.application.usecases.status;

import com.microservice.users.domain.models.Status;
import com.microservice.users.domain.ports.input.status.CreateStatusUseCase;
import com.microservice.users.domain.ports.output.StatusRepositoryPort;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CreateStatusUseCaseImpl implements CreateStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;

    @Override
    public Status create(Status estado) {
        return statusRepositoryPort.create(estado);
    }
}
