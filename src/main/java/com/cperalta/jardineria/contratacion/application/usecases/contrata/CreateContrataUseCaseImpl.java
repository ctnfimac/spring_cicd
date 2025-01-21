package com.cperalta.jardineria.contratacion.application.usecases.contrata;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;
import com.cperalta.jardineria.contratacion.domain.ports.input.contrata.CreateContrataUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.ContrataRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateContrataUseCaseImpl implements CreateContrataUseCase {

    private final ContrataRepositoryPort contrataRepositoryPort;

    @Override
    public Contrata create(Contrata contrata) {
        return contrataRepositoryPort.create(contrata);
    }
}
