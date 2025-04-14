package com.microservice.users.servicios.infraestructure.repositories;

import com.microservice.users.servicios.domain.models.TipoDeServicio;
import com.microservice.users.servicios.domain.ports.output.TipoDeServicioRepositoryPort;
import com.microservice.users.servicios.infraestructure.entities.TipoDeServicioEntity;
import com.microservice.users.servicios.infraestructure.mapper.TipoDeServicioMapper;
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
    public List<TipoDeServicio> getAll() {
        List<TipoDeServicioEntity> tiposDeServicio = jpaTipoDeServicioRepository.findAll();
        return tiposDeServicio.stream()
                .map(tipoDeServicioMapper::tipoDeServicioEntityToTipoDeServicio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoDeServicio> getById(Long id) {
        return jpaTipoDeServicioRepository.findById(id).map(tipoDeServicioMapper::tipoDeServicioEntityToTipoDeServicio);
    }

    @Override
    public TipoDeServicio create(TipoDeServicio tipoDeServicio) {
        TipoDeServicioEntity tipoDeServicioEntity = tipoDeServicioMapper.tipoDeServicioToTipoDeServicioEntity(tipoDeServicio);
        TipoDeServicioEntity tipoDeServicioCreado = jpaTipoDeServicioRepository.save(tipoDeServicioEntity);
        return tipoDeServicioMapper.tipoDeServicioEntityToTipoDeServicio(tipoDeServicioCreado);
    }

    @Override
    public Optional<TipoDeServicio> update(Long id, TipoDeServicio tipoDeServicio) {
        if(jpaTipoDeServicioRepository.existsById(id)){
            TipoDeServicioEntity tipoDeServicioEntityActual = jpaTipoDeServicioRepository.getById(id);
            tipoDeServicioEntityActual.setNombre(
                    tipoDeServicio.getNombre()!= null? tipoDeServicio.getNombre() : tipoDeServicioEntityActual.getNombre()
            );
            tipoDeServicioEntityActual.setFoto(tipoDeServicio.getFoto() != null ?
                    tipoDeServicio.getFoto() : tipoDeServicioEntityActual.getFoto()
            );

            TipoDeServicioEntity tipoDeServicioActualizado = jpaTipoDeServicioRepository.save(tipoDeServicioEntityActual);
            return Optional.of(tipoDeServicioMapper.tipoDeServicioEntityToTipoDeServicio(tipoDeServicioActualizado));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        if(jpaTipoDeServicioRepository.existsById(id)){
            jpaTipoDeServicioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
