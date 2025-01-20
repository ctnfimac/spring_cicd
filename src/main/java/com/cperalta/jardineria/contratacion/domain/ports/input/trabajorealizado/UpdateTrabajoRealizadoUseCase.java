package com.cperalta.jardineria.contratacion.domain.ports.input.trabajorealizado;

import com.cperalta.jardineria.contratacion.domain.models.TrabajoRealizado;

import java.util.Optional;

public interface UpdateTrabajoRealizadoUseCase {
    Optional<TrabajoRealizado> update(Long id, TrabajoRealizado trabajoRealizado);
}
