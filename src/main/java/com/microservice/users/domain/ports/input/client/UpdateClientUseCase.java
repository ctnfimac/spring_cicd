package com.microservice.users.domain.ports.input.client;

import com.microservice.users.domain.models.Client;

public interface UpdateClientUseCase {
    Client update(Long id, Client cliente);
}
