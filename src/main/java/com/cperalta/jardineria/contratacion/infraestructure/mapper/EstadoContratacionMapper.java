package com.cperalta.jardineria.contratacion.infraestructure.mapper;

import com.cperalta.jardineria.contratacion.domain.models.EstadoContratacion;
import com.cperalta.jardineria.contratacion.infraestructure.dto.EstadoContratacionRequestDTO;
import com.cperalta.jardineria.contratacion.infraestructure.entities.EstadoContratacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoContratacionMapper {
    EstadoContratacion estadoContratacionEntityToEstadoContratacion(EstadoContratacionEntity estadoContratacionEntity);
    EstadoContratacionEntity estadoContratacionToEstadoContratacionEntity(EstadoContratacion estadoContratacion);

    EstadoContratacion estadoContratacionRequestDTOtoEstadoDeContratacion(EstadoContratacionRequestDTO estadoContratacionRequestDTO);
}
