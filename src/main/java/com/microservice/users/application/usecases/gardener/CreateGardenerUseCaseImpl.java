package com.microservice.users.application.usecases.gardener;

import com.microservice.users.domain.models.Gardener;
import com.microservice.users.domain.ports.input.gardener.CreateGardenerUseCase;
import com.microservice.users.domain.ports.output.GardenerRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateGardenerUseCaseImpl implements CreateGardenerUseCase {

    private final GardenerRepositoryPort gardenerRepositoryPort;

    @Override
    public Gardener create(Gardener gardener) {
        return gardenerRepositoryPort.create(gardener);
    }
}
