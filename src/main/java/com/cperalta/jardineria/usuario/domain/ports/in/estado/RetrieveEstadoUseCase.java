package com.cperalta.jardineria.usuario.domain.ports.in.estado;

import com.cperalta.jardineria.usuario.domain.models.Estado;

import java.util.List;
import java.util.Optional;

public interface RetrieveEstadoUseCase {
    Optional<Estado> getEstadoById(Long id);
    List<Estado> getAllEstados();
}
