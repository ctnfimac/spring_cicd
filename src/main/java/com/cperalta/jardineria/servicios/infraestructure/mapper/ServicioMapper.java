package com.cperalta.jardineria.servicios.infraestructure.mapper;

import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.infraestructure.entities.ServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServicioMapper {
    Servicio servicioEntityToServicio(ServicioEntity servicioEntity);
    ServicioEntity servicioToServicioEntity(Servicio servicio);
}
