package com.microservice.users.application.usecases.gardener;

import com.microservice.users.domain.ports.input.gardener.DeleteGardenerUseCase;
import com.microservice.users.domain.ports.output.GardenerRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteGardenerUseCaseImpl implements DeleteGardenerUseCase {

    private final GardenerRepositoryPort gardenerRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return gardenerRepositoryPort.delete(id);
    }
}
