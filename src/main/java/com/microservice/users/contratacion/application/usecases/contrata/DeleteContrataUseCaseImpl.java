package com.microservice.users.contratacion.application.usecases.contrata;

import com.microservice.users.contratacion.domain.ports.input.contrata.DeleteContrataUseCase;
import com.microservice.users.contratacion.domain.ports.output.ContrataRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteContrataUseCaseImpl implements DeleteContrataUseCase {

    private final ContrataRepositoryPort contrataRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return contrataRepositoryPort.delete(id);
    }
}
