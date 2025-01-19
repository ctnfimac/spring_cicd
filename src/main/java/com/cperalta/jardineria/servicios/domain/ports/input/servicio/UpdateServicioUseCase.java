package com.cperalta.jardineria.servicios.domain.ports.input.servicio;

import com.cperalta.jardineria.servicios.domain.models.Servicio;

import java.util.Optional;
import java.util.UUID;

public interface UpdateServicioUseCase {
    Optional<Servicio> update(UUID id, Servicio servicio);
}
