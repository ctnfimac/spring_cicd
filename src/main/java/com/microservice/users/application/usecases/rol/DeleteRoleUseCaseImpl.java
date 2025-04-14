package com.microservice.users.application.usecases.rol;

import com.microservice.users.domain.ports.input.role.DeleteRoleUseCase;
import com.microservice.users.domain.ports.output.RoleRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteRoleUseCaseImpl implements DeleteRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return roleRepositoryPort.delete(id);
    }
}
