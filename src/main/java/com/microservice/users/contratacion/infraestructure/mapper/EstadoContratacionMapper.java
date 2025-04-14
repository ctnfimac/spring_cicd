package com.microservice.users.contratacion.infraestructure.mapper;

import com.microservice.users.contratacion.domain.models.EstadoContratacion;
import com.microservice.users.contratacion.infraestructure.dto.EstadoContratacionRequestDTO;
import com.microservice.users.contratacion.infraestructure.entities.EstadoContratacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoContratacionMapper {
    EstadoContratacion estadoContratacionEntityToEstadoContratacion(EstadoContratacionEntity estadoContratacionEntity);
    EstadoContratacionEntity estadoContratacionToEstadoContratacionEntity(EstadoContratacion estadoContratacion);

    EstadoContratacion estadoContratacionRequestDTOtoEstadoDeContratacion(EstadoContratacionRequestDTO estadoContratacionRequestDTO);
}
