package com.microservice.users.application.usecases.client;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.ports.input.client.UpdateClientUseCase;
import com.microservice.users.domain.ports.output.ClientRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateClientUseCaseImpl implements UpdateClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Client update(Long id, Client cliente) {
        return clientRepositoryPort.update(id, cliente);
    }
}
