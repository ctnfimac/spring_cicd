package com.microservice.users.contratacion.application.usecases.contrata;

import com.microservice.users.contratacion.domain.models.Contrata;
import com.microservice.users.contratacion.domain.ports.input.contrata.RetrieveContrataUseCase;
import com.microservice.users.contratacion.domain.ports.output.ContrataRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveContrataUseCaseImpl implements RetrieveContrataUseCase {

    private final ContrataRepositoryPort contrataRepositoryPort;

    @Override
    public Optional<Contrata> getById(Long id) {
        return contrataRepositoryPort.getById(id);
    }

    @Override
    public List<Contrata> getAll() {
        return contrataRepositoryPort.getAll();
    }
}
