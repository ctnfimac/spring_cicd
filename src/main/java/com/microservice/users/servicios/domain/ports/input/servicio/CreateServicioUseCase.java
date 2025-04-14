package com.microservice.users.servicios.domain.ports.input.servicio;

import com.microservice.users.servicios.domain.models.Servicio;

public interface CreateServicioUseCase {
    Servicio create(Servicio servicio);
}
