package com.cperalta.jardineria.usuario.domain.ports.input.role;

import com.cperalta.jardineria.usuario.domain.models.Role;

import java.util.Optional;

public interface UpdateRoleUseCase {
    Optional<Role> update(Long id, Role role);
}
