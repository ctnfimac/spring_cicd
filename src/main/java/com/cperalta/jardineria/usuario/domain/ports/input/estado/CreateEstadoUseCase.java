package com.cperalta.jardineria.usuario.domain.ports.input.estado;

import com.cperalta.jardineria.usuario.domain.models.Estado;

public interface CreateEstadoUseCase {
    Estado createEstado(Estado estado);
}
