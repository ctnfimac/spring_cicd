package com.cperalta.jardineria.usuario.domain.ports.input.rol;

import com.cperalta.jardineria.usuario.domain.models.Rol;

import java.util.List;
import java.util.Optional;

public interface RetrieveRolUseCase {
    Optional<Rol> getById(Long id);
    List<Rol> getAll();
}
