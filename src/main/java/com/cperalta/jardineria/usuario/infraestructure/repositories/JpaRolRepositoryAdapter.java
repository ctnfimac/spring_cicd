package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.domain.ports.output.RolRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.entities.RolEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.RolMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaRolRepositoryAdapter implements RolRepositoryPort {

    private final JpaRolRepository jpaRolRepository;
    private final RolMapper rolMapper;

    @Override
    public Optional<Rol> getById(Long id) {
        return jpaRolRepository.findById(id).map(rolMapper::rolEntityToRol);
    }

    @Override
    public List<Rol> getAll() {
        return jpaRolRepository.findAll().stream()
                .map(rolMapper::rolEntityToRol)
                .collect(Collectors.toList());
    }

    @Override
    public Rol create(Rol rol) {
        try {
            RolEntity rolEntity = rolMapper.rolToRolEntity(rol);
            RolEntity rolNuevo = jpaRolRepository.save(rolEntity);
            return rolMapper.rolEntityToRol(rolNuevo);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El rol con la descripción '" + rol.getDescripcion() + "' ya existe.");
        }

    }

    @Override
    public boolean delete(Long id) {
        if(jpaRolRepository.existsById(id)){
            jpaRolRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Rol> update(Long id, Rol rol) {
        try{
            if(jpaRolRepository.existsById(id)){
                RolEntity rolEntityActual = jpaRolRepository.findById(id).get();
                rolEntityActual.setDescripcion(rol.getDescripcion());
                RolEntity rolEntityActualizado = jpaRolRepository.save(rolEntityActual);
                return Optional.of(rolMapper.rolEntityToRol(rolEntityActualizado));
            }
            return Optional.empty();
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El rol con la descripción '" + rol.getDescripcion() + "' ya existe.");
        }
    }
}
