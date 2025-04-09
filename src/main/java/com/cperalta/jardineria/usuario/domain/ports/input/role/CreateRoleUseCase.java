package com.cperalta.jardineria.usuario.domain.ports.input.role;

import com.cperalta.jardineria.usuario.domain.models.Role;

public interface CreateRoleUseCase {
    Role create(Role role);
}
