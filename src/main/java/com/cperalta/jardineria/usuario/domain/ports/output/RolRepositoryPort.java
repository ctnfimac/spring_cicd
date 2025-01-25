package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Rol;

import java.util.List;
import java.util.Optional;

public interface RolRepositoryPort {
    Optional<Rol> getById(Long id);
    List<Rol> getAll();
    Rol create(Rol rol);
    boolean delete(Long id);
    Optional<Rol> update(Long id, Rol rol);
}
