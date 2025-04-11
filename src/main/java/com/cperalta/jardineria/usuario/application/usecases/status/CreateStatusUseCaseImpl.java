package com.cperalta.jardineria.usuario.application.usecases.status;

import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.domain.ports.input.status.CreateStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.StatusRepositoryPort;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CreateStatusUseCaseImpl implements CreateStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;

    @Override
    public Status create(Status estado) {
        return statusRepositoryPort.create(estado);
    }
}
