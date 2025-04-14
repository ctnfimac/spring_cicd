package com.microservice.users.domain.ports.input.status;

import com.microservice.users.domain.models.Status;
import java.util.Optional;

public interface UpdateStatusUseCase {
    Optional<Status> update(Long id, Status status);
}
