package com.microservice.users.servicios.domain.ports.input.tipoDeServicio;

import com.microservice.users.servicios.domain.models.TipoDeServicio;

public interface CreateTipoDeServicioUseCase {
    TipoDeServicio create(TipoDeServicio tipoDeServicio);
}
