package com.microservice.users.application.usecases.client;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.ports.input.client.CreateClientUseCase;
import com.microservice.users.domain.ports.output.ClientRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Client create(Client client) {
        return clientRepositoryPort.create(client);
    }
}
