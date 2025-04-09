package com.cperalta.jardineria.usuario.domain.ports.input.role;

import com.cperalta.jardineria.usuario.domain.models.Role;

import java.util.List;
import java.util.Optional;

public interface RetrieveRoleUseCase {
    Optional<Role> getById(Long id);
    List<Role> getAll();
}
