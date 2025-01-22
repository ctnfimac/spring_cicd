package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.CreateClienteUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.DeleteClienteUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.RetrieveClienteUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.UpdateClienteUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ClienteService implements RetrieveClienteUseCase, CreateClienteUseCase,
        UpdateClienteUseCase, DeleteClienteUseCase {

    private final RetrieveClienteUseCase retrieveClienteUseCase;
    private final CreateClienteUseCase createClienteUseCase;
    private final UpdateClienteUseCase updateClienteUseCase;
    private final DeleteClienteUseCase deleteClienteUseCase;

    @Override
    public Cliente create(Cliente cliente) {
        return createClienteUseCase.create(cliente);
    }

    @Override
    public boolean delete(Long id) {
        return deleteClienteUseCase.delete(id);
    }

    @Override
    public Optional<Cliente> getById(Long id) {
        return retrieveClienteUseCase.getById(id);
    }

    @Override
    public List<Cliente> getAll() {
        return retrieveClienteUseCase.getAll();
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        return updateClienteUseCase.update(id, cliente);
    }
}
