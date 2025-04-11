package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.BaseUser;
import com.cperalta.jardineria.usuario.infraestructure.entities.BaseUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BaseUserMapper {
    BaseUser baseUserEntityToBaseUser(BaseUserEntity baseUserEntity);
    BaseUserEntity baseUserToBaseUserEntity(BaseUser baseUser);
}
