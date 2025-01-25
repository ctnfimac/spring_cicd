package com.cperalta.jardineria.usuario.domain.ports.input.rol;

import com.cperalta.jardineria.usuario.domain.models.Rol;

public interface CreateRolUseCase {
    Rol create(Rol rol);
}
