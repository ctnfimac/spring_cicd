package com.cperalta.jardineria.usuario.application.usecases.client;

import com.cperalta.jardineria.usuario.domain.ports.input.client.DeleteClientUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.ClientRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteClientUseCaseImpl implements DeleteClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return clientRepositoryPort.delete(id);
    }
}
