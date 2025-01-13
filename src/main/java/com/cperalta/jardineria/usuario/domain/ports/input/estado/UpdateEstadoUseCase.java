package com.cperalta.jardineria.usuario.domain.ports.input.estado;

import com.cperalta.jardineria.usuario.domain.models.Estado;

import java.util.Optional;

public interface UpdateEstadoUseCase {
    Optional<Estado> updateEstado(Long id, Estado estado);
}
