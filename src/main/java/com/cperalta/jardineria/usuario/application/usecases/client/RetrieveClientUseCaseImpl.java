package com.cperalta.jardineria.usuario.application.usecases.client;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.ports.input.client.RetrieveClientUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.ClientRepositoryPort;
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
