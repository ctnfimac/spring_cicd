package com.microservice.users.domain.ports.input.gardener;

import com.microservice.users.domain.models.Gardener;

import java.util.Optional;
import java.util.List;

public interface RetrieveGardenerUseCase {
    Optional<Gardener> getByEmail(String email);
    Optional<Gardener> getByEmailAndPassword(String email, String password);

    Optional<Gardener> getById(Long id);
    List<Gardener> getAll();
}
