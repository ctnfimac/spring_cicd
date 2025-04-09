package com.cperalta.jardineria.usuario.application.usecases.rol;

import com.cperalta.jardineria.usuario.domain.models.Role;
import com.cperalta.jardineria.usuario.domain.ports.input.role.UpdateRoleUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.RoleRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Optional<Role> update(Long id, Role role) {
        return roleRepositoryPort.update(id, role);
    }
}
