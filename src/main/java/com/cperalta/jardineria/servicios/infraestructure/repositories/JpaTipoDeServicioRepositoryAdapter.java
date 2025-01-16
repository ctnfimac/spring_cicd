package com.cperalta.jardineria.servicios.infraestructure.repositories;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import com.cperalta.jardineria.servicios.infraestructure.entities.TipoDeServicioEntity;
import com.cperalta.jardineria.servicios.infraestructure.mapper.TipoDeServicioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaTipoDeServicioRepositoryAdapter implements TipoDeServicioRepositoryPort {

    private final JpaTipoDeServicioRepository jpaTipoDeServicioRepository;
    private final TipoDeServicioMapper tipoDeServicioMapper;

    @Override
    public List<TipoDeServicio> getAllTiposDeServicios() {
        List<TipoDeServicioEntity> tiposDeServicio = jpaTipoDeServicioRepository.findAll();
        return tiposDeServicio.stream()
                .map(tipoDeServicioMapper::tipoDeServicioEntityToTipoDeServicio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoDeServicio> getTipoDeServicioById(Long id) {
        return jpaTipoDeServicioRepository.findById(id).map(tipoDeServicioMapper::tipoDeServicioEntityToTipoDeServicio);
    }

    @Override
    public TipoDeServicio createTipoDeServicio(TipoDeServicio tipoDeServicio) {
        return null;
    }

    @Override
    public Optional<TipoDeServicio> updateTipoDeServicio(TipoDeServicio tipoDeServicio) {
        return Optional.empty();
    }

    @Override
    public boolean deleteTipoDeServicio(Long id) {
        return false;
    }
}
