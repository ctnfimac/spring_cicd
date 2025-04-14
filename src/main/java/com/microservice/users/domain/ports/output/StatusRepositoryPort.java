package com.microservice.users.domain.ports.output;

import com.microservice.users.domain.models.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepositoryPort {
    Optional<Status> findById(Long id);
    List<Status> findAll();
    Status create(Status status);
    boolean delete(Long id);
    Optional<Status> update(Long id, Status status);
}
