package com.cperalta.jardineria.usuario.application.usecases.gardener;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.ports.input.gardener.UpdateGardenerUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.GardenerRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateGardenerUseCaseImpl implements UpdateGardenerUseCase {

    private final GardenerRepositoryPort gardenerRepositoryPort;

    @Override
    public Gardener update(Long id, Gardener gardener) {
        return gardenerRepositoryPort.update(id, gardener);
    }
}
