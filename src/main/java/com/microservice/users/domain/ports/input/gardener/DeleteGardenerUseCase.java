package com.microservice.users.domain.ports.input.gardener;

public interface DeleteGardenerUseCase {
    boolean delete(Long id);
}
