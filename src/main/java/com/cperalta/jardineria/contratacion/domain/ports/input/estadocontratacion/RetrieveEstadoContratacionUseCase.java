package com.cperalta.jardineria.contratacion.domain.ports.input.estadocontratacion;

import com.cperalta.jardineria.contratacion.domain.models.EstadoContratacion;
import java.util.List;
import java.util.Optional;

public interface RetrieveEstadoContratacionUseCase {
    List<EstadoContratacion> getAll();
    Optional<EstadoContratacion> getById(Long id);
}
