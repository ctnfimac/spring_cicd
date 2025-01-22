package com.cperalta.jardineria.usuario.domain.ports.input.cliente;

import com.cperalta.jardineria.usuario.domain.models.Cliente;

public interface CreateClienteUseCase {
    Cliente create(Cliente cliente);
}
