package com.microservice.users.application.usecases.client;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.ports.input.client.RetrieveClientUseCase;
import com.microservice.users.domain.ports.output.ClientRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveClientUseCaseImpl implements RetrieveClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Optional<Client> getById(Long id) {
        return clientRepositoryPort.getById(id);
    }

    @Override
    public List<Client> getAll() {
        return clientRepositoryPort.getAll();
    }
}
