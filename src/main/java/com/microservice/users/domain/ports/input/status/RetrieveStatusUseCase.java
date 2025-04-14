package com.microservice.users.domain.ports.input.status;

import com.microservice.users.domain.models.Status;

import java.util.List;
import java.util.Optional;

public interface RetrieveStatusUseCase {
    Optional<Status> getById(Long id);
    List<Status> getAll();
}
