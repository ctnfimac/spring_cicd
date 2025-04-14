package com.microservice.users.servicios.domain.ports.input.tipoDeServicio;

import com.microservice.users.servicios.domain.models.TipoDeServicio;

import java.util.Optional;

public interface UpdateTipoDeServicioUseCase {
   Optional<TipoDeServicio> update(Long id, TipoDeServicio tipoDeServicio);
}
