package com.cperalta.jardineria.servicios.domain.ports.output;

import com.cperalta.jardineria.servicios.domain.models.Servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServicioRepositoryPort {
    Optional<Servicio> getById(UUID id);
    List<Servicio> getAll();
}
