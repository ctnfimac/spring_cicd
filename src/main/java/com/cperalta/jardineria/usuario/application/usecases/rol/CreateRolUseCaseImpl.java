package com.cperalta.jardineria.usuario.application.usecases.rol;

import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.domain.ports.input.rol.CreateRolUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.RolRepositoryPort;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CreateRolUseCaseImpl implements CreateRolUseCase {

    private final RolRepositoryPort rolRepositoryPort;

    @Override
    public Rol create(Rol rol) {
        return rolRepositoryPort.create(rol);
    }

}
