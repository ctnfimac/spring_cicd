package com.cperalta.jardineria.contratacion.domain.ports.input.contrata;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;

import java.util.Optional;

public interface UpdateContrataUseCase {
    Optional<Contrata> update(Long id, Contrata contrata);
}
