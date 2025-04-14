package com.microservice.users.domain.ports.input.client;

import com.microservice.users.domain.models.Client;

public interface CreateClientUseCase {
    Client create(Client cliente);
}
