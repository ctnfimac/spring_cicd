package com.microservice.users.servicios.infraestructure.mapper;

import com.microservice.users.servicios.domain.models.TipoDeServicio;
import com.microservice.users.servicios.infraestructure.dto.TipoDeServicioDTO;
import com.microservice.users.servicios.infraestructure.dto.TipoDeServicioUpdateRequestDTO;
import com.microservice.users.servicios.infraestructure.entities.TipoDeServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TipoDeServicioMapper {
    TipoDeServicio tipoDeServicioEntityToTipoDeServicio(TipoDeServicioEntity tipoDeServicioEntity);
    TipoDeServicioEntity tipoDeServicioToTipoDeServicioEntity(TipoDeServicio tipoDeServicio);
    TipoDeServicioDTO tipoDeServicioToTipoDeServicioDTO(TipoDeServicio tipoDeServicio);
    TipoDeServicio tipoDeServicioDTOtoTipoDeServicio(TipoDeServicioDTO tipoDeServicioDTO);
    TipoDeServicio tipodeServicioUpdateRequestDTOtoTipoDeServicio(TipoDeServicioUpdateRequestDTO tipoDeServicioUpdateRequestDTO);
}
