package com.cperalta.jardineria.servicios.application.usecases.tipodeservicio;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.servicios.domain.ports.input.tipoDeServicio.RetrieveTipoDeServicioUseCase;
import com.cperalta.jardineria.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveTipoDeServicioUseCaseImpl implements RetrieveTipoDeServicioUseCase {

    private final TipoDeServicioRepositoryPort tipoDeServicioRepositoryPort;

    @Override
    public List<TipoDeServicio> getAllTiposDeServicios() {
        return tipoDeServicioRepositoryPort.getAllTiposDeServicios();
    }

    @Override
    public Optional<TipoDeServicio> getTipoDeServicioById(Long id) {
        return tipoDeServicioRepositoryPort.getTipoDeServicioById(id);
    }
}
