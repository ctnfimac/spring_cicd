package com.cperalta.jardineria.servicios.domain.ports.input.tipoDeServicio;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;

public interface CreateTipoDeServicioUseCase {
    TipoDeServicio createTipoDeServicio(TipoDeServicio tipoDeServicio);
}
