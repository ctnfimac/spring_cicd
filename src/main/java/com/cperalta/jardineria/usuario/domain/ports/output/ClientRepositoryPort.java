package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryPort {
    Optional<Client> getById(Long id);
    List<Client> getAll();
    Client create(Client client);
    Client update(Long id, Client client);
    boolean delete(Long id);
}
