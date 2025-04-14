package com.microservice.users.servicios.application.usecases.tipodeservicio;

import com.microservice.users.servicios.domain.ports.input.tipoDeServicio.DeleteTipoDeServicioUseCase;
import com.microservice.users.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTipoDeServicioUseCaseImpl implements DeleteTipoDeServicioUseCase {
    private final TipoDeServicioRepositoryPort tipoDeServicioRepositoryPort;

    public boolean delete(Long id){
        return tipoDeServicioRepositoryPort.delete(id);
    }
}
