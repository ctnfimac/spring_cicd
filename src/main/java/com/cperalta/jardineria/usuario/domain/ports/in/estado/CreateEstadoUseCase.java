package com.cperalta.jardineria.usuario.domain.ports.in.estado;

import com.cperalta.jardineria.usuario.domain.models.Estado;

public interface CreateEstadoUseCase {
    Estado createEstado(Estado estado);
}
