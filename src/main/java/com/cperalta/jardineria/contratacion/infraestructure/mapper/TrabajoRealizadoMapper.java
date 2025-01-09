package com.cperalta.jardineria.contratacion.infraestructure.mapper;

import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.infraestructure.entities.ServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TrabajoRealizadoMapper {
    Servicio servicioEntityToServicio(ServicioEntity servicioEntity);
    ServicioEntity servicioToservicioEntity(Servicio servicio);
}
