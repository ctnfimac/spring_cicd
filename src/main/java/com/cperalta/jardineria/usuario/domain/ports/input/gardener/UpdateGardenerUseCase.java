package com.cperalta.jardineria.usuario.domain.ports.input.gardener;

import com.cperalta.jardineria.usuario.domain.models.Gardener;

public interface UpdateGardenerUseCase {
    Gardener update(Long id, Gardener gardener);
}
