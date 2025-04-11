package com.cperalta.jardineria.usuario.domain.ports.input.status;

import com.cperalta.jardineria.usuario.domain.models.Status;

import java.util.List;
import java.util.Optional;

public interface RetrieveStatusUseCase {
    Optional<Status> getById(Long id);
    List<Status> getAll();
}
