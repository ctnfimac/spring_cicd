package com.cperalta.jardineria.usuario.domain.ports.input.cliente;

import com.cperalta.jardineria.usuario.domain.models.Cliente;

import java.util.Optional;
import java.util.List;

public interface RetrieveClienteUseCase {
    Optional<Cliente> getById(Long id);
    List<Cliente> getAll();
}
