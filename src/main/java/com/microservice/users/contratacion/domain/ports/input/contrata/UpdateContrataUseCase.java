package com.microservice.users.contratacion.domain.ports.input.contrata;

import com.microservice.users.contratacion.domain.models.Contrata;

import java.util.Optional;

public interface UpdateContrataUseCase {
    Optional<Contrata> update(Long id, Contrata contrata);
}
