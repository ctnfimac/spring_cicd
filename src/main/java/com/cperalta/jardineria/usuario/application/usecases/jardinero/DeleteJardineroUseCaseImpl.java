package com.cperalta.jardineria.usuario.application.usecases.jardinero;

import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.DeleteJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.JardineroRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteJardineroUseCaseImpl implements DeleteJardineroUseCase {

    private final JardineroRepositoryPort jardineroRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return jardineroRepositoryPort.delete(id);
    }
}
