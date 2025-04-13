package com.cperalta.jardineria.servicios.infraestructure.repositories;

import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.domain.ports.output.ServicioRepositoryPort;
import com.cperalta.jardineria.servicios.infraestructure.entities.ServicioEntity;
import com.cperalta.jardineria.servicios.infraestructure.entities.TipoDeServicioEntity;
import com.cperalta.jardineria.servicios.infraestructure.mapper.ServicioMapper;
import com.cperalta.jardineria.usuario.infraestructure.entities.GardenerEntity;
import com.cperalta.jardineria.usuario.infraestructure.repositories.JpaGardenerRepository;
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
    private final JpaTipoDeServicioRepository jpaTipoDeServicioRepository;
    private final JpaGardenerRepository jpaJardineroRepository;

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

    @Override
    public Servicio create(Servicio servicio) {
        ServicioEntity servicioEntity = servicioMapper.servicioToServicioEntity(servicio);
        ServicioEntity servicioEntityNuevo = jpaServicioRepository.save(servicioEntity);
        return servicioMapper.servicioEntityToServicio(servicioEntityNuevo);
    }

    @Override
    public Optional<Servicio> update(UUID id, Servicio servicio) {
        if(jpaServicioRepository.existsById(id)){
            ServicioEntity servicioEntityActual = jpaServicioRepository.findById(id).get();

            servicioEntityActual.setDescripcion(servicio.getDescripcion() != null ?
                        servicio.getDescripcion() : servicioEntityActual.getDescripcion()
            );

            servicioEntityActual.setPrecio(servicio.getPrecio() != null ?
                        servicio.getPrecio() : servicioEntityActual.getPrecio()
            );

            if(servicio.getTipoDeServicio() != null ) {
                TipoDeServicioEntity tipoDeServicioEntity = jpaTipoDeServicioRepository.getById(servicio.getTipoDeServicio().getId());
                servicioEntityActual.setTipoDeServicio(tipoDeServicioEntity);
            }

            if(servicio.getGardener() != null ) {
                GardenerEntity jardineroEntity = jpaJardineroRepository.getById(servicio.getTipoDeServicio().getId());
                servicioEntityActual.setGardener(jardineroEntity);
            }

            ServicioEntity servicioEntityActualizado = jpaServicioRepository.save(servicioEntityActual);
            return Optional.of(servicioMapper.servicioEntityToServicio(servicioEntityActualizado));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(UUID id) {
        if(jpaServicioRepository.existsById(id)){
            jpaServicioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
