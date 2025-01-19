package com.cperalta.jardineria.servicios.domain.ports.input.servicio;

import com.cperalta.jardineria.servicios.domain.models.Servicio;

public interface CreateServicioUseCase {
    Servicio create(Servicio servicio);
}
