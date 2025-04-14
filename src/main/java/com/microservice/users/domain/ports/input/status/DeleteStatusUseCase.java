package com.microservice.users.domain.ports.input.status;

public interface DeleteStatusUseCase {
    boolean delete(Long id);
}
