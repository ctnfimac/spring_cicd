package com.microservice.users.domain.ports.input.client;

public interface DeleteClientUseCase {
    boolean delete(Long id);
}
