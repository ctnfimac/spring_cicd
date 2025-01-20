package com.cperalta.jardineria.contratacion.infraestructure.repositories;

import com.cperalta.jardineria.contratacion.domain.models.TrabajoRealizado;
import com.cperalta.jardineria.contratacion.domain.ports.output.TrabajoRealizadoRepositoryPort;
import com.cperalta.jardineria.contratacion.infraestructure.entities.TrabajoRealizadoEntity;
import com.cperalta.jardineria.contratacion.infraestructure.mapper.TrabajoRealizadoMapper;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import com.cperalta.jardineria.usuario.infraestructure.repositories.JpaJardineroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaTrabajoRealizadoRepositoryAdapter implements TrabajoRealizadoRepositoryPort {

    private final JpaTrabajoRealizadoRepository jpaTrabajoRealizadoRepository;
    private final JpaJardineroRepository jpaJardineroRepository;

    private final TrabajoRealizadoMapper trabajoRealizadoMapper;
    private final JardineroMapper jardineroMapper;

    @Override
    public List<TrabajoRealizado> getAll() {
        return jpaTrabajoRealizadoRepository.findAll().stream()
                .map(trabajoRealizadoMapper::trabajoRealizadoEntityToTrabajoRealizado)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TrabajoRealizado> getById(Long id) {
        return jpaTrabajoRealizadoRepository.findById(id).map(trabajoRealizadoMapper::trabajoRealizadoEntityToTrabajoRealizado);
    }

    @Override
    public TrabajoRealizado create(TrabajoRealizado trabajoRealizado) {
        if(jpaJardineroRepository.existsById(trabajoRealizado.getJardinero().getId())){
            JardineroEntity jardineroEntity = jpaJardineroRepository.findById(trabajoRealizado.getJardinero().getId()).get();
            TrabajoRealizadoEntity trabajoRealizadoEntity = trabajoRealizadoMapper.trabajoRealizadoToTrabajoRealizadoEntity(trabajoRealizado);
            trabajoRealizadoEntity.setJardinero(jardineroEntity);
            TrabajoRealizadoEntity trabajoRealizadoCreado = jpaTrabajoRealizadoRepository.save(trabajoRealizadoEntity);
            return trabajoRealizadoMapper.trabajoRealizadoEntityToTrabajoRealizado(trabajoRealizadoCreado);
        }
        return null;
    }

    @Override
    public Optional<TrabajoRealizado> update(Long id, TrabajoRealizado trabajoRealizado) {
        if(jpaTrabajoRealizadoRepository.existsById(id)){
            TrabajoRealizadoEntity trabajoRealizadoActual = jpaTrabajoRealizadoRepository.findById(id).get();

            trabajoRealizadoActual.setDescripcion( trabajoRealizado.getDescripcion() != null ?
                trabajoRealizado.getDescripcion() : trabajoRealizadoActual.getDescripcion()
            );

            trabajoRealizadoActual.setFoto( trabajoRealizado.getFoto() != null ?
                    trabajoRealizado.getFoto() : trabajoRealizadoActual.getFoto()
            );

            if(trabajoRealizado.getJardinero() != null){
                JardineroEntity jardineroEntity = jpaJardineroRepository.findById(trabajoRealizado.getJardinero().getId()).get();
                trabajoRealizadoActual.setJardinero(jardineroEntity);
            }
            TrabajoRealizadoEntity trabajoRealizadoActualizado = jpaTrabajoRealizadoRepository.save(trabajoRealizadoActual);
            return Optional.ofNullable(trabajoRealizadoMapper.trabajoRealizadoEntityToTrabajoRealizado(trabajoRealizadoActualizado));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        if(jpaTrabajoRealizadoRepository.existsById(id)){
            jpaTrabajoRealizadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
