package com.cperalta.jardineria.contratacion.domain.ports.input.contrata;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;

import java.util.Optional;
import java.util.List;

public interface RetrieveContrataUseCase {
    Optional<Contrata> getById(Long id);
    List<Contrata> getAll();
}
