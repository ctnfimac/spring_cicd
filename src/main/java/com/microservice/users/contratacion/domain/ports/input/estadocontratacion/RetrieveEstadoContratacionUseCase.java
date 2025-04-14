package com.microservice.users.contratacion.domain.ports.input.estadocontratacion;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;
import java.util.List;
import java.util.Optional;

public interface RetrieveEstadoContratacionUseCase {
    List<EstadoContratacion> getAll();
    Optional<EstadoContratacion> getById(Long id);
}
