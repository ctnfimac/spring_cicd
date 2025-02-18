package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.RegistrarClienteUseCase;
import com.cperalta.jardineria.usuario.domain.records.ClienteRecord;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrarClienteService implements RegistrarClienteUseCase {

    private final RegistrarClienteUseCase registrarClienteUseCase;

    @Override
    public Cliente registrar(ClienteRecord clienteRecord) {
        return registrarClienteUseCase.registrar(clienteRecord);
    }

    @Override
    public Boolean activar(String email, String token) {
        return registrarClienteUseCase.activar(email, token);
    }
}
