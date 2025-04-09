package com.cperalta.jardineria.usuario.application.usecases.rol;

import com.cperalta.jardineria.usuario.domain.models.Role;
import com.cperalta.jardineria.usuario.domain.ports.input.role.RetrieveRoleUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.RoleRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveRoleUseCaseImpl implements RetrieveRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Optional<Role> getById(Long id) {
        return roleRepositoryPort.getById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepositoryPort.getAll();
    }
}
