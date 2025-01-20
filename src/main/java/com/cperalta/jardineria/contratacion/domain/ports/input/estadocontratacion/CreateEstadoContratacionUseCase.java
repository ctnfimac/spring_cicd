package com.cperalta.jardineria.contratacion.domain.ports.input.estadocontratacion;

import com.cperalta.jardineria.contratacion.domain.models.EstadoContratacion;

public interface CreateEstadoContratacionUseCase {
    EstadoContratacion create(EstadoContratacion estadoContratacion);
}
