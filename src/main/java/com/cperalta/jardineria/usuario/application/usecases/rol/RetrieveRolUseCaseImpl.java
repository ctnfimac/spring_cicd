package com.cperalta.jardineria.usuario.application.usecases.rol;

import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.domain.ports.input.rol.RetrieveRolUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.RolRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveRolUseCaseImpl implements RetrieveRolUseCase {

    private final RolRepositoryPort rolRepositoryPort;

    @Override
    public Optional<Rol> getById(Long id) {
        return rolRepositoryPort.getById(id);
    }

    @Override
    public List<Rol> getAll() {
        return rolRepositoryPort.getAll();
    }
}
