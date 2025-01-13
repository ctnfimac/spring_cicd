package com.cperalta.jardineria.usuario.application.usecases.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.RetrieveJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.JardineroRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveJardineroUseCaseImpl implements RetrieveJardineroUseCase {

    private JardineroRepositoryPort jardineroRepositoryPort;

    @Override
    public Optional<Jardinero> getByEmail(String email) {
        return jardineroRepositoryPort.findByEmail(email);
    }

    @Override
    public Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia) {
        return jardineroRepositoryPort.getByEmailAndContrasenia(email, contrasenia);
    }
}
