package com.microservice.users.infraestructure.mapper;

import com.microservice.users.domain.models.BaseUser;
import com.microservice.users.infraestructure.entities.BaseUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BaseUserMapper {
    BaseUser baseUserEntityToBaseUser(BaseUserEntity baseUserEntity);
    BaseUserEntity baseUserToBaseUserEntity(BaseUser baseUser);
}
