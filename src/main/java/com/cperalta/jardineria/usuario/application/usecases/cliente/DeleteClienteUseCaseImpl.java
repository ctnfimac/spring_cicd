package com.cperalta.jardineria.usuario.application.usecases.cliente;

import com.cperalta.jardineria.usuario.domain.ports.input.cliente.DeleteClienteUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.ClienteRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteClienteUseCaseImpl implements DeleteClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return clienteRepositoryPort.delete(id);
    }
}
