package com.microservice.users.servicios.domain.ports.input.tipoDeServicio;

import com.microservice.users.servicios.domain.models.TipoDeServicio;
import java.util.List;
import java.util.Optional;

public interface RetrieveTipoDeServicioUseCase {
    List<TipoDeServicio> getAll();
    Optional<TipoDeServicio> getById(Long id);
}
