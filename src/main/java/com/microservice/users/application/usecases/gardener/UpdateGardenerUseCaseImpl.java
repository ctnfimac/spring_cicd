package com.microservice.users.application.usecases.gardener;

import com.microservice.users.domain.models.Gardener;
import com.microservice.users.domain.ports.input.gardener.UpdateGardenerUseCase;
import com.microservice.users.domain.ports.output.GardenerRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateGardenerUseCaseImpl implements UpdateGardenerUseCase {

    private final GardenerRepositoryPort gardenerRepositoryPort;

    @Override
    public Gardener update(Long id, Gardener gardener) {
        return gardenerRepositoryPort.update(id, gardener);
    }
}
