package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.records.ClientRecord;

public interface RegisterClientRepositoryPort {
    Client register(ClientRecord clientRecord);
    Boolean activate(String email, String token);
}
