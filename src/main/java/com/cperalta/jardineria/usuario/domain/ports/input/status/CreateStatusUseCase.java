package com.cperalta.jardineria.usuario.domain.ports.input.status;

import com.cperalta.jardineria.usuario.domain.models.Status;

public interface CreateStatusUseCase {
    Status create(Status status);
}
