package com.cperalta.jardineria.contratacion.domain.ports.input.estadocontratacion;

import com.cperalta.jardineria.contratacion.domain.models.EstadoContratacion;

import java.util.Optional;

public interface UpdateEstadoContratacionUseCase {
    Optional<EstadoContratacion> update(Long id, EstadoContratacion estadoContratacion);
}
