package com.cperalta.jardineria.usuario.domain.ports.input.cliente;

import com.cperalta.jardineria.usuario.domain.models.Cliente;

public interface UpdateClienteUseCase {
    Cliente update(Long id, Cliente cliente);
}
