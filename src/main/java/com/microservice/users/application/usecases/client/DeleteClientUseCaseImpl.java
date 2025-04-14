package com.microservice.users.application.usecases.client;

import com.microservice.users.domain.ports.input.client.DeleteClientUseCase;
import com.microservice.users.domain.ports.output.ClientRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteClientUseCaseImpl implements DeleteClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return clientRepositoryPort.delete(id);
    }
}
