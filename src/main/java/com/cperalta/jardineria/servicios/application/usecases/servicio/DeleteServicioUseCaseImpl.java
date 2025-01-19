package com.cperalta.jardineria.servicios.application.usecases.servicio;

import com.cperalta.jardineria.servicios.domain.ports.input.servicio.DeleteServicioUseCase;
import com.cperalta.jardineria.servicios.domain.ports.output.ServicioRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class DeleteServicioUseCaseImpl implements DeleteServicioUseCase {
    private final ServicioRepositoryPort servicioRepositoryPort;

    public boolean delete(UUID id){
        return servicioRepositoryPort.delete(id);
    }
}
