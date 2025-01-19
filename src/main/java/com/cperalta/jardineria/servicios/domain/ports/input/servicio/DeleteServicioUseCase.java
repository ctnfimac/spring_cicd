package com.cperalta.jardineria.servicios.domain.ports.input.servicio;

import java.util.UUID;

public interface DeleteServicioUseCase {
    boolean delete(UUID id);
}
