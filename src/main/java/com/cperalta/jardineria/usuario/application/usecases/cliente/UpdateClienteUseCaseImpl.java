package com.cperalta.jardineria.usuario.application.usecases.cliente;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.UpdateClienteUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.ClienteRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateClienteUseCaseImpl implements UpdateClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente update(Long id, Cliente cliente) {
        return clienteRepositoryPort.update(id, cliente);
    }
}
