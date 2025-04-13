package com.cperalta.jardineria.usuario.application.usecases.client;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.ports.input.client.CreateClientUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.ClientRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Client create(Client client) {
        return clientRepositoryPort.create(client);
    }
}
