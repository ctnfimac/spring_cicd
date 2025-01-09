package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.TipoDeServicio;
import com.cperalta.jardineria.usuario.infraestructure.entities.TipoDeServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TipoDeServicioMapper {
    TipoDeServicio tipoDeServicioEntityToTipoDeServicio(TipoDeServicioEntity tipoDeServicioEntity);
    TipoDeServicioEntity tipoDeServicioToTipoDeServicioEntity(TipoDeServicio tipoDeServicio);
}
