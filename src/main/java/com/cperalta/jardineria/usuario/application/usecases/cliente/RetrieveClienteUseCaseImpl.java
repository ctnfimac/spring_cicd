package com.cperalta.jardineria.usuario.application.usecases.cliente;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.RetrieveClienteUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.ClienteRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveClienteUseCaseImpl implements RetrieveClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Optional<Cliente> getById(Long id) {
        return clienteRepositoryPort.getById(id);
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepositoryPort.getAll();
    }
}
