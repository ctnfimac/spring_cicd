package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.CreateJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.DeleteJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.RetrieveJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.UpdateJardineroUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class JardineroService implements RetrieveJardineroUseCase, CreateJardineroUseCase,
        UpdateJardineroUseCase, DeleteJardineroUseCase {

    private final RetrieveJardineroUseCase retrieveJardineroUseCase;
    private final CreateJardineroUseCase createJardineroUseCase;
    private final UpdateJardineroUseCase updateJardineroUseCase;
    private final DeleteJardineroUseCase deleteJardineroUseCase;

    @Override
    public Optional<Jardinero> getByEmail(String email) {
        return retrieveJardineroUseCase.getByEmail(email);
    }

    @Override
    public Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia) {
        return retrieveJardineroUseCase.getByEmailAndContrasenia(email ,contrasenia);
    }

    @Override
    public Optional<Jardinero> getById(Long id) {
        return retrieveJardineroUseCase.getById(id);
    }

    @Override
    public List<Jardinero> getAll() {
        return retrieveJardineroUseCase.getAll();
    }

    @Override
    public Jardinero create(Jardinero jardinero) {
        return createJardineroUseCase.create(jardinero);
    }

    @Override
    public boolean delete(Long id) {
        return deleteJardineroUseCase.delete(id);
    }

    @Override
    public Jardinero update(Long id, Jardinero jardinero) {
        return updateJardineroUseCase.update(id, jardinero);
    }
}
