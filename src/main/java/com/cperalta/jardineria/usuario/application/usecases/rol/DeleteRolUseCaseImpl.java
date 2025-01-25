package com.cperalta.jardineria.usuario.application.usecases.rol;

import com.cperalta.jardineria.usuario.domain.ports.input.rol.DeleteRolUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.RolRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteRolUseCaseImpl implements DeleteRolUseCase {

    private final RolRepositoryPort rolRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return rolRepositoryPort.delete(id);
    }
}
