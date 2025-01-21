package com.cperalta.jardineria.contratacion.domain.ports.input.contrata;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;

public interface CreateContrataUseCase {
    Contrata create(Contrata contrata);
}
