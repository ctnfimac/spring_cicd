package com.cperalta.jardineria.usuario.domain.ports.input.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;

import java.util.Optional;

public interface RetrieveJardineroUseCase {
    Optional<Jardinero> getByEmail(String email);
    Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia);
}
