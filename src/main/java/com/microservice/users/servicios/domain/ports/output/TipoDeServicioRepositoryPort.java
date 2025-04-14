package com.microservice.users.servicios.domain.ports.output;

import com.microservice.users.servicios.domain.models.TipoDeServicio;

import java.util.List;
import java.util.Optional;

public interface TipoDeServicioRepositoryPort {
    List<TipoDeServicio> getAll();
    Optional<TipoDeServicio> getById(Long id);
    TipoDeServicio create(TipoDeServicio tipoDeServicio);
    Optional<TipoDeServicio> update(Long id,TipoDeServicio tipoDeServicio);
    boolean delete(Long id);
}
