package com.cperalta.jardineria.usuario.domain.ports.input.cliente;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.records.ClienteRecord;

public interface RegistrarClienteUseCase {
    Cliente registrar(ClienteRecord clienteRecord);
    Boolean activar(String email, String token);
}
