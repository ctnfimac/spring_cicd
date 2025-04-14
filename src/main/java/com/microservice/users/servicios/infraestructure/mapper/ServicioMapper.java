package com.microservice.users.servicios.infraestructure.mapper;

import com.microservice.users.servicios.domain.models.Servicio;
import com.microservice.users.servicios.infraestructure.dto.ServicioRequestDTO;
import com.microservice.users.servicios.infraestructure.dto.ServicioResponseDTO;
import com.microservice.users.servicios.infraestructure.dto.ServicioUpdateRequestDTO;
import com.microservice.users.servicios.infraestructure.entities.ServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServicioMapper {
    Servicio servicioEntityToServicio(ServicioEntity servicioEntity);
    ServicioEntity servicioToServicioEntity(Servicio servicio);
    ServicioResponseDTO servicioToServicioResponseDTO(Servicio servicio);
    Servicio servicioDTOToServicio(ServicioResponseDTO servicioResponseDTO);

    Servicio servicioRequestDTOtoServicio(ServicioRequestDTO servicioRequestDTO);
    Servicio servicioUpdateRequestDTOtoServicio(ServicioUpdateRequestDTO servicioUpdateRequestDTO);
}
