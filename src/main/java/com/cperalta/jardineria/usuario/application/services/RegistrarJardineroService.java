package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.RegistrarJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrarJardineroService implements RegistrarJardineroUseCase {

    private final RegistrarJardineroUseCase registrarJardineroUseCase;

    @Override
    public Jardinero registrar(JardineroRecord jardineroRecord) {
        return registrarJardineroUseCase.registrar(jardineroRecord);
    }

    @Override
    public Boolean activar(String email, String token) {
        return registrarJardineroUseCase.activar(email, token);
    }

}
