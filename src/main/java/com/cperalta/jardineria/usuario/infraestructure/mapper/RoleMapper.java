package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Role;
import com.cperalta.jardineria.usuario.infraestructure.dto.RoleDTO;
import com.cperalta.jardineria.usuario.infraestructure.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role roleEntityToRole(RoleEntity roleEntity);
    RoleEntity roleToRoleEntity(Role role);

    Role roleDTOtoRole(RoleDTO roleDTO);
}
