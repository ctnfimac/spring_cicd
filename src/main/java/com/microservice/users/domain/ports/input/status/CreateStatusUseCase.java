package com.microservice.users.domain.ports.input.status;

import com.microservice.users.domain.models.Status;

public interface CreateStatusUseCase {
    Status create(Status status);
}
