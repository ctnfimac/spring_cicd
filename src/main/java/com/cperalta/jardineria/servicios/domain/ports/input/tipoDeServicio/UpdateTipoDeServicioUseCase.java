package com.cperalta.jardineria.servicios.domain.ports.input.tipoDeServicio;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;

import java.util.Optional;

public interface UpdateTipoDeServicioUseCase {
   Optional<TipoDeServicio> update(Long id, TipoDeServicio tipoDeServicio);
}
