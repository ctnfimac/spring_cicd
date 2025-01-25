package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.domain.ports.input.rol.CreateRolUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.rol.DeleteRolUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.rol.RetrieveRolUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.rol.UpdateRolUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RolService implements RetrieveRolUseCase, CreateRolUseCase,
        DeleteRolUseCase, UpdateRolUseCase {

    private final RetrieveRolUseCase retrieveRolUseCase;
    private final CreateRolUseCase createRolUseCase;
    private final DeleteRolUseCase deleteRolUseCase;
    private final UpdateRolUseCase updateRolUseCase;

    @Override
    public Rol create(Rol rol) {
        return createRolUseCase.create(rol);
    }

    @Override
    public boolean delete(Long id) {
        return deleteRolUseCase.delete(id);
    }

    @Override
    public Optional<Rol> getById(Long id) {
        return retrieveRolUseCase.getById(id);
    }

    @Override
    public List<Rol> getAll() {
        return retrieveRolUseCase.getAll();
    }

    @Override
    public Optional<Rol> update(Long id, Rol rol) {
        return updateRolUseCase.update(id, rol);
    }
}
