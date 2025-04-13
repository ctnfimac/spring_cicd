package com.cperalta.jardineria.usuario.domain.ports.input.client;

import com.cperalta.jardineria.usuario.domain.models.Client;

public interface CreateClientUseCase {
    Client create(Client cliente);
}
