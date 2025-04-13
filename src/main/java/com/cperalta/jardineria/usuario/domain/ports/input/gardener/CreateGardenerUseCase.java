package com.cperalta.jardineria.usuario.domain.ports.input.gardener;

import com.cperalta.jardineria.usuario.domain.models.Gardener;

public interface CreateGardenerUseCase {
    Gardener create(Gardener gardener);
}
