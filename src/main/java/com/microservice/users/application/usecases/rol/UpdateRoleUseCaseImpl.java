package com.microservice.users.application.usecases.rol;

import com.microservice.users.domain.models.Role;
import com.microservice.users.domain.ports.input.role.UpdateRoleUseCase;
import com.microservice.users.domain.ports.output.RoleRepositoryPort;
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
