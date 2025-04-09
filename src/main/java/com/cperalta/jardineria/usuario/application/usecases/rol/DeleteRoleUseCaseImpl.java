package com.cperalta.jardineria.usuario.application.usecases.rol;

import com.cperalta.jardineria.usuario.domain.ports.input.role.DeleteRoleUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.RoleRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteRoleUseCaseImpl implements DeleteRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return roleRepositoryPort.delete(id);
    }
}
