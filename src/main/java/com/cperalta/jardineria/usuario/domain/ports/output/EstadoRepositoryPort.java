package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Estado;

import java.util.List;
import java.util.Optional;

public interface EstadoRepositoryPort {
    Optional<Estado> findById(Long id);
    List<Estado> findAll();
    Estado create(Estado estado);
    boolean delete(Long id);
    Optional<Estado> update(Long id, Estado estado);
}
