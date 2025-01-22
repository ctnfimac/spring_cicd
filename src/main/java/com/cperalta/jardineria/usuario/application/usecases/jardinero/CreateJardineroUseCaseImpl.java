package com.cperalta.jardineria.usuario.application.usecases.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.CreateJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.JardineroRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateJardineroUseCaseImpl implements CreateJardineroUseCase {

    private final JardineroRepositoryPort jardineroRepositoryPort;

    @Override
    public Jardinero create(Jardinero jardinero) {
        return jardineroRepositoryPort.create(jardinero);
    }
}
