package com.cperalta.jardineria.usuario.domain.ports.input.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;

import java.util.Optional;
import java.util.List;

public interface RetrieveJardineroUseCase {
    Optional<Jardinero> getByEmail(String email);
    Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia);

    Optional<Jardinero> getById(Long id);
    List<Jardinero> getAll();
}
