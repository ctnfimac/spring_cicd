package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Gardener;

import java.util.List;
import java.util.Optional;

public interface GardenerRepositoryPort {
    Optional<Gardener> findByEmail(String email);
    Optional<Gardener> getByEmailAndPassword(String email, String password);

    Optional<Gardener> getById(Long id);
    List<Gardener> getAll();
    Gardener create(Gardener gardener);
    Gardener update(Long id, Gardener gardener);
    boolean delete(Long id);
}
