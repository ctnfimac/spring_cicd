package com.cperalta.jardineria.usuario.application.usecases.status;

import com.cperalta.jardineria.usuario.domain.ports.input.status.RetrieveStatusUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.StatusRepositoryPort;
import com.cperalta.jardineria.usuario.domain.models.Status;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveStatusUseCaseImpl implements RetrieveStatusUseCase {

    private final StatusRepositoryPort statusRepositoryPort;

    @Override
    public Optional<Status> getById(Long id) {
        return statusRepositoryPort.findById(id);
    }

    @Override
    public List<Status> getAll() {
        return statusRepositoryPort.findAll();
    }
}
