package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.models.EstadoContratacion;
import com.cperalta.jardineria.usuario.infraestructure.entities.EstadoContratacionEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.EstadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoContratacionMapper {
    EstadoContratacion estadoContratacionEntityToEstadoContratacion(EstadoContratacion estadoContratacion);
    EstadoContratacionEntity estadoContratacionToEstadoContratacionEntity(EstadoContratacion estadoContratacion);
}
