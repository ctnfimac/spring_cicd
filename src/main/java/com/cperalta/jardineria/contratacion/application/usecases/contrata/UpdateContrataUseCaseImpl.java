package com.cperalta.jardineria.contratacion.application.usecases.contrata;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;
import com.cperalta.jardineria.contratacion.domain.ports.input.contrata.UpdateContrataUseCase;
import com.cperalta.jardineria.contratacion.domain.ports.output.ContrataRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateContrataUseCaseImpl implements UpdateContrataUseCase {

    private final ContrataRepositoryPort contrataRepositoryPort;

    @Override
    public Optional<Contrata> update(Long id, Contrata contrata) {
        return contrataRepositoryPort.update(id, contrata);
    }
}
