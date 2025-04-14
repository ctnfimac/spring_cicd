package com.microservice.users.contratacion.domain.ports.output;

import com.microservice.users.contratacion.domain.models.Contrata;

import java.util.List;
import java.util.Optional;

public interface ContrataRepositoryPort {
    Optional<Contrata> getById(Long id);
    List<Contrata> getAll();
    Contrata create(Contrata contrata);
    Optional<Contrata> update(Long id, Contrata contrata);
    boolean delete(Long id);
}
