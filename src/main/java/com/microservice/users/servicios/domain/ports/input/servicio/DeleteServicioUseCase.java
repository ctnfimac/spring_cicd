package com.microservice.users.servicios.domain.ports.input.servicio;

import java.util.UUID;

public interface DeleteServicioUseCase {
    boolean delete(UUID id);
}
