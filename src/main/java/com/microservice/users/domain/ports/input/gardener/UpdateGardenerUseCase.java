package com.microservice.users.domain.ports.input.gardener;

import com.microservice.users.domain.models.Gardener;

public interface UpdateGardenerUseCase {
    Gardener update(Long id, Gardener gardener);
}
