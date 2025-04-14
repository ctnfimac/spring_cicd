package com.microservice.users.domain.ports.input.client;

import com.microservice.users.domain.models.Client;

import java.util.Optional;
import java.util.List;

public interface RetrieveClientUseCase {
    Optional<Client> getById(Long id);
    List<Client> getAll();
}
