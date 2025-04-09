package com.cperalta.jardineria.usuario.application.usecases.rol;

import com.cperalta.jardineria.usuario.domain.models.Role;
import com.cperalta.jardineria.usuario.domain.ports.input.role.CreateRoleUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.RoleRepositoryPort;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Role create(Role role) {
        return roleRepositoryPort.create(role);
    }

}
