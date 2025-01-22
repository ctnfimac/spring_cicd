package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {
    Optional<Cliente> getById(Long id);
    List<Cliente> getAll();
    Cliente create(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    boolean delete(Long id);
}
