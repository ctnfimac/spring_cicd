package com.cperalta.jardineria.contratacion.application.usecases.contrata;

import com.cperalta.jardineria.contratacion.domain.ports.input.contrata.DeleteContrataUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.ContrataRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteContrataUseCaseImpl implements DeleteContrataUseCase {

    private final ContrataRepositoryPort contrataRepositoryPort;

    @Override
    public boolean delete(Long id) {
        return contrataRepositoryPort.delete(id);
    }
}
