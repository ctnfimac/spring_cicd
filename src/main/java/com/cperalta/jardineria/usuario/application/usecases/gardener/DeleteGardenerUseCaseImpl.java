package com.cperalta.jardineria.usuario.application.usecases.gardener;

import com.cperalta.jardineria.usuario.domain.ports.input.gardener.DeleteGardenerUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.GardenerRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteGardenerUseCaseImpl implements DeleteGardenerUseCase {

    private final GardenerRepositoryPort gardenerRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return gardenerRepositoryPort.delete(id);
    }
}
