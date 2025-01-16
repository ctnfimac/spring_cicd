package com.cperalta.jardineria.servicios.infraestructure.repositories;

import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.domain.ports.output.ServicioRepositoryPort;
import com.cperalta.jardineria.servicios.infraestructure.mapper.ServicioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaServicioRepositoryAdapter implements ServicioRepositoryPort {

    private final JpaServicioRepository jpaServicioRepository;
    private final ServicioMapper servicioMapper;

    @Override
    public Optional<Servicio> getById(UUID id) {
        return jpaServicioRepository.findById(id).map(servicioMapper::servicioEntityToServicio);
    }

    @Override
    public List<Servicio> getAll() {
        return jpaServicioRepository.findAll().stream()
                .map(servicioMapper::servicioEntityToServicio)
                .collect(Collectors.toList());
    }
}
