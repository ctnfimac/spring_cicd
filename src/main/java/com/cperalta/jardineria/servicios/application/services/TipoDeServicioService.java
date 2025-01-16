package com.cperalta.jardineria.servicios.application.services;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.servicios.domain.ports.input.tipoDeServicio.RetrieveTipoDeServicioUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TipoDeServicioService implements RetrieveTipoDeServicioUseCase {

    private final RetrieveTipoDeServicioUseCase retrieveTipoDeServicioUseCase;

    @Override
    public List<TipoDeServicio> getAllTiposDeServicios() {
        return retrieveTipoDeServicioUseCase.getAllTiposDeServicios();
    }

    @Override
    public Optional<TipoDeServicio> getTipoDeServicioById(Long id) {
        return retrieveTipoDeServicioUseCase.getTipoDeServicioById(id);
    }
}
