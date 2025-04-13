package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.ports.input.client.RegisterClientUseCase;
import com.cperalta.jardineria.usuario.domain.records.ClientRecord;
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
