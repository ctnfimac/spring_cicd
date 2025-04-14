package com.microservice.users.domain.ports.input.client;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.records.ClientRecord;

public interface RegisterClientUseCase {
    Client register(ClientRecord clientRecord);
    Boolean activate(String email, String token);
}
