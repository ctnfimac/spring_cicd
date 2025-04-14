package com.microservice.users.contratacion.application.usecases.contrata;

import com.microservice.users.contratacion.domain.models.Contrata;
import com.microservice.users.contratacion.domain.ports.input.contrata.CreateContrataUseCase;
import com.microservice.users.contratacion.domain.ports.output.ContrataRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateContrataUseCaseImpl implements CreateContrataUseCase {

    private final ContrataRepositoryPort contrataRepositoryPort;

    @Override
    public Contrata create(Contrata contrata) {
        return contrataRepositoryPort.create(contrata);
    }
}
