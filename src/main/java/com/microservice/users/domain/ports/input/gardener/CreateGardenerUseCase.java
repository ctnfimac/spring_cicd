package com.microservice.users.domain.ports.input.gardener;

import com.microservice.users.domain.models.Gardener;

public interface CreateGardenerUseCase {
    Gardener create(Gardener gardener);
}
