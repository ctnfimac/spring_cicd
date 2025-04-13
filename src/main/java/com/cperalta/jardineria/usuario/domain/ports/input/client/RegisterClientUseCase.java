package com.cperalta.jardineria.usuario.domain.ports.input.client;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.records.ClientRecord;

public interface RegisterClientUseCase {
    Client register(ClientRecord clientRecord);
    Boolean activate(String email, String token);
}
