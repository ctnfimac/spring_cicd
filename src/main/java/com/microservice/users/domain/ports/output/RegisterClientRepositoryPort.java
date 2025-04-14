package com.microservice.users.domain.ports.output;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.records.ClientRecord;

public interface RegisterClientRepositoryPort {
    Client register(ClientRecord clientRecord);
    Boolean activate(String email, String token);
}
