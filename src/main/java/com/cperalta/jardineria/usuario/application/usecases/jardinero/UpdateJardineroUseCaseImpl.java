package com.cperalta.jardineria.usuario.application.usecases.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.UpdateJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.JardineroRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateJardineroUseCaseImpl implements UpdateJardineroUseCase {

    private final JardineroRepositoryPort jardineroRepositoryPort;

    @Override
    public Jardinero update(Long id, Jardinero jardinero) {
        return jardineroRepositoryPort.update(id, jardinero);
    }
}
