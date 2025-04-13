package com.cperalta.jardineria.usuario.domain.ports.input.client;

import com.cperalta.jardineria.usuario.domain.models.Client;

import java.util.Optional;
import java.util.List;

public interface RetrieveClientUseCase {
    Optional<Client> getById(Long id);
    List<Client> getAll();
}
