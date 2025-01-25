package com.cperalta.jardineria.usuario.domain.ports.input.rol;

import com.cperalta.jardineria.usuario.domain.models.Rol;

import java.util.Optional;

public interface UpdateRolUseCase {
    Optional<Rol> update(Long id, Rol rol);
}
