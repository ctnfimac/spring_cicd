package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.infraestructure.dto.EstadoRequestDTO;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.infraestructure.entities.EstadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoMapper {
    Estado estadoEntityToEstado(EstadoEntity estadoEntity);
    EstadoEntity estadoToEstadoEntity(Estado estado);
    EstadoRequestDTO estadoTOEstadoRequestDTO(Estado estado);
    Estado estadoRequestDTOtoEstado(EstadoRequestDTO estadoRequestDTO);
}
