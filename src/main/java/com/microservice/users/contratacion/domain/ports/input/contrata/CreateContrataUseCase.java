package com.microservice.users.contratacion.domain.ports.input.contrata;

import com.microservice.users.contratacion.domain.models.Contrata;

public interface CreateContrataUseCase {
    Contrata create(Contrata contrata);
}
