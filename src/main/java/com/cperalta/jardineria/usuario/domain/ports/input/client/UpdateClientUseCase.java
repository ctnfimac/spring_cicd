package com.cperalta.jardineria.usuario.domain.ports.input.client;

import com.cperalta.jardineria.usuario.domain.models.Client;

public interface UpdateClientUseCase {
    Client update(Long id, Client cliente);
}
