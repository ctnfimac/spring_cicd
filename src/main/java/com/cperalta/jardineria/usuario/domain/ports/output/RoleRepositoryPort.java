package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> getById(Long id);
    List<Role> getAll();
    Role create(Role role);
    boolean delete(Long id);
    Optional<Role> update(Long id, Role role);
}
