package com.cperalta.jardineria.servicios.domain.ports.input.servicio;

import com.cperalta.jardineria.servicios.domain.models.Servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RetrieveServicioUseCase {
    Optional<Servicio> getById(UUID id);
    List<Servicio> getAll();
}
