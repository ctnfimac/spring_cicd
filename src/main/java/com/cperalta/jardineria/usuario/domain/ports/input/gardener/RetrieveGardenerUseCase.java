package com.cperalta.jardineria.usuario.domain.ports.input.gardener;

import com.cperalta.jardineria.usuario.domain.models.Gardener;

import java.util.Optional;
import java.util.List;

public interface RetrieveGardenerUseCase {
    Optional<Gardener> getByEmail(String email);
    Optional<Gardener> getByEmailAndPassword(String email, String password);

    Optional<Gardener> getById(Long id);
    List<Gardener> getAll();
}
