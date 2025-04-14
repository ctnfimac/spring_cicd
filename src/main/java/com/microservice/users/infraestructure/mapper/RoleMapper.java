package com.microservice.users.infraestructure.mapper;

import com.microservice.users.domain.models.Role;
import com.microservice.users.infraestructure.dto.RoleDTO;
import com.microservice.users.infraestructure.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role roleEntityToRole(RoleEntity roleEntity);
    RoleEntity roleToRoleEntity(Role role);

    Role roleDTOtoRole(RoleDTO roleDTO);
}
