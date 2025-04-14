package com.microservice.users.application.services;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.ports.input.client.RegisterClientUseCase;
import com.microservice.users.domain.records.ClientRecord;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterClientService implements RegisterClientUseCase {

    private final RegisterClientUseCase registrarClienteUseCase;

    @Override
    public Client register(ClientRecord clientRecord) {
        return registrarClienteUseCase.register(clientRecord);
    }

    @Override
    public Boolean activate(String email, String token) {
        return registrarClienteUseCase.activate(email, token);
    }
}
