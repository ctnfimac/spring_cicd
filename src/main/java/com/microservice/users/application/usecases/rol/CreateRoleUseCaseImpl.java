package com.microservice.users.application.usecases.rol;

import com.microservice.users.domain.models.Role;
import com.microservice.users.domain.ports.input.role.CreateRoleUseCase;
import com.microservice.users.domain.ports.output.RoleRepositoryPort;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Role create(Role role) {
        return roleRepositoryPort.create(role);
    }

}
