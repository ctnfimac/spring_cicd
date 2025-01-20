package com.cperalta.jardineria.contratacion.domain.ports.input.trabajorealizado;

import com.cperalta.jardineria.contratacion.domain.models.TrabajoRealizado;

public interface CreateTrabajoRealizadoUseCase {
    TrabajoRealizado create(TrabajoRealizado trabajoRealizado);
}
