package com.cperalta.jardineria.servicios.application.usecases.tipodeservicio;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.servicios.domain.ports.input.tipoDeServicio.CreateTipoDeServicioUseCase;
import com.cperalta.jardineria.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTipoDeServicioUseCaseImpl implements CreateTipoDeServicioUseCase {
    private final TipoDeServicioRepositoryPort tipoDeServicioRepositoryPort;

    public TipoDeServicio create(TipoDeServicio tipoDeServicio){
        return tipoDeServicioRepositoryPort.create(tipoDeServicio);
    }
}
