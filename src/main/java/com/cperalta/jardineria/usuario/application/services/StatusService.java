package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.ports.input.status.CreateStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.status.DeleteStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.status.RetrieveStatusUseCase;
import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.domain.ports.input.status.UpdateStatusUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class StatusService implements RetrieveStatusUseCase, CreateStatusUseCase,
        DeleteStatusUseCase, UpdateStatusUseCase {

    private final RetrieveStatusUseCase retrieveStatusUseCase;
    private final CreateStatusUseCase createStatusUseCase;
    private final DeleteStatusUseCase deleteStatusUseCase;
    private final UpdateStatusUseCase updateStatusUseCase;

    @Override
    public Optional<Status> getById(Long id) {
        return retrieveStatusUseCase.getById(id);
    }

    @Override
    public List<Status> getAll() {
        return retrieveStatusUseCase.getAll();
    }

    @Override
    public Status create(Status status) {
        return createStatusUseCase.create(status);
    }

    @Override
    public boolean delete(Long id) {
        return deleteStatusUseCase.delete(id);
    }

    @Override
    public Optional<Status> update(Long id, Status status) {
        return updateStatusUseCase.update(id, status);
    }
}
