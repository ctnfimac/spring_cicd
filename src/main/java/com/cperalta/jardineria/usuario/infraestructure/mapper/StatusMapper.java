package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.infraestructure.dto.StatusRequestDTO;
import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.infraestructure.entities.StatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatusMapper {
    Status statusEntityToStatus(StatusEntity statusEntity);
    StatusEntity statusToStatusEntity(Status status);
    StatusRequestDTO statusTOStatusRequestDTO(Status status);
    Status statusRequestDTOtoStatus(StatusRequestDTO statusRequestDTO);
}
