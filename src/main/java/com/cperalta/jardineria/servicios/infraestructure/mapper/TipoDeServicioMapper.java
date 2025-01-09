package com.cperalta.jardineria.servicios.infraestructure.mapper;

import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.servicios.infraestructure.entities.TipoDeServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TipoDeServicioMapper {
    TipoDeServicio tipoDeServicioEntityToTipoDeServicio(TipoDeServicioEntity tipoDeServicioEntity);
    TipoDeServicioEntity tipoDeServicioToTipoDeServicioEntity(TipoDeServicio tipoDeServicio);
}
