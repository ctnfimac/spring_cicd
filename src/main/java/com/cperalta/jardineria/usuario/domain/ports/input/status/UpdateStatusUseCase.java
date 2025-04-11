package com.cperalta.jardineria.usuario.domain.ports.input.status;

import com.cperalta.jardineria.usuario.domain.models.Status;
import java.util.Optional;

public interface UpdateStatusUseCase {
    Optional<Status> update(Long id, Status status);
}
