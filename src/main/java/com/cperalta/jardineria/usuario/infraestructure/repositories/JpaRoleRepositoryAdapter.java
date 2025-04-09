package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Role;
import com.cperalta.jardineria.usuario.domain.ports.output.RoleRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.entities.RoleEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaRoleRepositoryAdapter implements RoleRepositoryPort {

    private final JpaRoleRepository jpaRoleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Optional<Role> getById(Long id) {
        return jpaRoleRepository.findById(id).map(roleMapper::roleEntityToRole);
    }

    @Override
    public List<Role> getAll() {
        return jpaRoleRepository.findAll().stream()
                .map(roleMapper::roleEntityToRole)
                .collect(Collectors.toList());
    }

    @Override
    public Role create(Role role) {
        try {
            RoleEntity roleEntity = roleMapper.roleToRoleEntity(role);
            RoleEntity roleNuevo = jpaRoleRepository.save(roleEntity);
            return roleMapper.roleEntityToRole(roleNuevo);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El rol con la descripción '" + role.getDescription() + "' ya existe.");
        }

    }

    @Override
    public boolean delete(Long id) {
        if(jpaRoleRepository.existsById(id)){
            jpaRoleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Role> update(Long id, Role role) {
        try{
            if(jpaRoleRepository.existsById(id)){
                RoleEntity roleEntityCurrent = jpaRoleRepository.findById(id).get();
                roleEntityCurrent.setDescription(role.getDescription());
                RoleEntity roleEntityUpdated = jpaRoleRepository.save(roleEntityCurrent);
                return Optional.of(roleMapper.roleEntityToRole(roleEntityUpdated));
            }
            return Optional.empty();
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El rol con la descripción '" + role.getDescription() + "' ya existe.");
        }
    }
}
