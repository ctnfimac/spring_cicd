package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;

import java.util.List;
import java.util.Optional;

public interface JardineroRepositoryPort {
    Optional<Jardinero> findByEmail(String email);
    Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia);

    Optional<Jardinero> getById(Long id);
    List<Jardinero> getAll();
    Jardinero create(Jardinero jardinero);
    Jardinero update(Long id, Jardinero jardinero);
    boolean delete(Long id);
}
