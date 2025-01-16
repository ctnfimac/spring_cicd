package com.cperalta.jardineria.servicios.domain.ports.output;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;

import java.util.List;
import java.util.Optional;

public interface TipoDeServicioRepositoryPort {
    List<TipoDeServicio> getAllTiposDeServicios();
    Optional<TipoDeServicio> getTipoDeServicioById(Long id);
    TipoDeServicio createTipoDeServicio(TipoDeServicio tipoDeServicio);
    Optional<TipoDeServicio> updateTipoDeServicio(TipoDeServicio tipoDeServicio);
    boolean deleteTipoDeServicio(Long id);
}
