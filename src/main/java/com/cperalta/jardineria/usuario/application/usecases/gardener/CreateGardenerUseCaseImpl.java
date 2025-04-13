package com.cperalta.jardineria.usuario.application.usecases.gardener;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.ports.input.gardener.CreateGardenerUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.GardenerRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateGardenerUseCaseImpl implements CreateGardenerUseCase {

    private final GardenerRepositoryPort gardenerRepositoryPort;

    @Override
    public Gardener create(Gardener gardener) {
        return gardenerRepositoryPort.create(gardener);
    }
}
