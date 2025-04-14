package com.microservice.users.infraestructure.mapper;

import com.microservice.users.domain.models.BaseUser;
import com.microservice.users.infraestructure.dto.LoginDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginMapper {
    @Mapping(target = "password", ignore = true)
    LoginDTO baseUserToLoginDTO(BaseUser baseUser);
}
