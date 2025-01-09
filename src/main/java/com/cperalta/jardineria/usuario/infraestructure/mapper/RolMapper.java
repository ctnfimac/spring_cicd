package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.infraestructure.entities.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RolMapper {
    Rol rolEntityToRol(RolEntity rolEntity);
    RolEntity rolToRolEntity(Rol rol);
}
