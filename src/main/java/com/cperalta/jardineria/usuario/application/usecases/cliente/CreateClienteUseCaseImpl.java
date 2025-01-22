package com.cperalta.jardineria.usuario.application.usecases.cliente;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.CreateClienteUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.ClienteRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClienteUseCaseImpl implements CreateClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente create(Cliente cliente) {
        return clienteRepositoryPort.create(cliente);
    }
}
