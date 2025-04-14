package com.microservice.users.contratacion.domain.ports.input.trabajorealizado;

import com.microservice.users.contratacion.domain.models.TrabajoRealizado;
import java.util.List;
import java.util.Optional;

public interface RetrieveTrabajoRealizadoUseCase {
    List<TrabajoRealizado> getAll();
    Optional<TrabajoRealizado> getById(Long id);
}
