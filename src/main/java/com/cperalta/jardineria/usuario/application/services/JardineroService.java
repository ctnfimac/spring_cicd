package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.RetrieveJardineroUseCase;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class JardineroService implements RetrieveJardineroUseCase {

    private final RetrieveJardineroUseCase retrieveJardineroUseCase;

    @Override
    public Optional<Jardinero> getByEmail(String email) {
        return retrieveJardineroUseCase.getByEmail(email);
    }

    @Override
    public Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia) {
        return retrieveJardineroUseCase.getByEmailAndContrasenia(email ,contrasenia);
    }
}
