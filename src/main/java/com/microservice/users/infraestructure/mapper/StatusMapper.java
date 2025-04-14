package com.microservice.users.infraestructure.mapper;

import com.microservice.users.infraestructure.dto.StatusRequestDTO;
import com.microservice.users.domain.models.Status;
import com.microservice.users.infraestructure.entities.StatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatusMapper {
    Status statusEntityToStatus(StatusEntity statusEntity);
    StatusEntity statusToStatusEntity(Status status);
    StatusRequestDTO statusTOStatusRequestDTO(Status status);
    Status statusRequestDTOtoStatus(StatusRequestDTO statusRequestDTO);
}
