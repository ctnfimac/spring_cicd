package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Role;
import com.cperalta.jardineria.usuario.domain.ports.input.role.CreateRoleUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.role.DeleteRoleUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.role.RetrieveRoleUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.role.UpdateRoleUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RoleService implements RetrieveRoleUseCase, CreateRoleUseCase,
        DeleteRoleUseCase, UpdateRoleUseCase {

    private final RetrieveRoleUseCase retrieveRoleUseCase;
    private final CreateRoleUseCase createRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;

    @Override
    public Role create(Role role) {
        return createRoleUseCase.create(role);
    }

    @Override
    public boolean delete(Long id) {
        return deleteRoleUseCase.delete(id);
    }

    @Override
    public Optional<Role> getById(Long id) {
        return retrieveRoleUseCase.getById(id);
    }

    @Override
    public List<Role> getAll() {
        return retrieveRoleUseCase.getAll();
    }

    @Override
    public Optional<Role> update(Long id, Role role) {
        return updateRoleUseCase.update(id, role);
    }
}
