package com.cperalta.jardineria.usuario.application.usecases.client;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.ports.input.client.UpdateClientUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.ClientRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateClientUseCaseImpl implements UpdateClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Client update(Long id, Client cliente) {
        return clientRepositoryPort.update(id, cliente);
    }
}
