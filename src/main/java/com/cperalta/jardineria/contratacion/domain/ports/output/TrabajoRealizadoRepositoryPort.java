package com.cperalta.jardineria.contratacion.domain.ports.output;

import com.cperalta.jardineria.contratacion.domain.models.TrabajoRealizado;

import java.util.List;
import java.util.Optional;

public interface TrabajoRealizadoRepositoryPort {
    List<TrabajoRealizado> getAll();
    Optional<TrabajoRealizado> getById(Long id);
    TrabajoRealizado create(TrabajoRealizado trabajoRealizado);
    Optional<TrabajoRealizado> update(Long id, TrabajoRealizado trabajoRealizado);
    boolean delete(Long id);
}
