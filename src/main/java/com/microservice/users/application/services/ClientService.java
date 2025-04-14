package com.microservice.users.application.services;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.ports.input.client.CreateClientUseCase;
import com.microservice.users.domain.ports.input.client.DeleteClientUseCase;
import com.microservice.users.domain.ports.input.client.RetrieveClientUseCase;
import com.microservice.users.domain.ports.input.client.UpdateClientUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ClientService implements RetrieveClientUseCase, CreateClientUseCase,
        UpdateClientUseCase, DeleteClientUseCase {

    private final RetrieveClientUseCase retrieveClientUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    @Override
    public Client create(Client client) {
        return createClientUseCase.create(client);
    }

    @Override
    public boolean delete(Long id) {
        return deleteClientUseCase.delete(id);
    }

    @Override
    public Optional<Client> getById(Long id) {
        return retrieveClientUseCase.getById(id);
    }

    @Override
    public List<Client> getAll() {
        return retrieveClientUseCase.getAll();
    }

    @Override
    public Client update(Long id, Client client) {
        return updateClientUseCase.update(id, client);
    }
}
