package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.records.ClienteRecord;

public interface RegistrarClienteRepositoryPort {
    Cliente registrar(ClienteRecord clienteRecord);
    Boolean activar(String email, String token);
}
