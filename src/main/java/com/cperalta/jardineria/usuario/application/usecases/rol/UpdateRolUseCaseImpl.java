package com.cperalta.jardineria.usuario.application.usecases.rol;

import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.domain.ports.input.rol.UpdateRolUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.RolRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateRolUseCaseImpl implements UpdateRolUseCase {

    private final RolRepositoryPort rolRepositoryPort;

    @Override
    public Optional<Rol> update(Long id, Rol rol) {
        return rolRepositoryPort.update(id, rol);
    }
}
