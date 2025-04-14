package com.microservice.users.contratacion.application.services;

import com.microservice.users.contratacion.domain.models.Contrata;
import com.microservice.users.contratacion.domain.ports.input.contrata.CreateContrataUseCase;
import com.microservice.users.contratacion.domain.ports.input.contrata.DeleteContrataUseCase;
import com.microservice.users.contratacion.domain.ports.input.contrata.RetrieveContrataUseCase;
import com.microservice.users.contratacion.domain.ports.input.contrata.UpdateContrataUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ContrataService implements RetrieveContrataUseCase, CreateContrataUseCase,
        UpdateContrataUseCase, DeleteContrataUseCase {

    final private RetrieveContrataUseCase retrieveContrataUseCase;
    final private CreateContrataUseCase createContrataUseCase;
    final private UpdateContrataUseCase updateContrataUseCase;
    final private DeleteContrataUseCase deleteContrataUseCase;

    @Override
    public Contrata create(Contrata contrata) {
        return createContrataUseCase.create(contrata);
    }

    @Override
    public boolean delete(Long id) {
        return deleteContrataUseCase.delete(id);
    }

    @Override
    public Optional<Contrata> getById(Long id) {
        return retrieveContrataUseCase.getById(id);
    }

    @Override
    public List<Contrata> getAll() {
        return retrieveContrataUseCase.getAll();
    }

    @Override
    public Optional<Contrata> update(Long id, Contrata contrata) {
        return updateContrataUseCase.update(id, contrata);
    }
}
