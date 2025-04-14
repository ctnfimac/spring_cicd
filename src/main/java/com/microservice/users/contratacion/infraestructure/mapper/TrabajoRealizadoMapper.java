package com.microservice.users.contratacion.infraestructure.mapper;

import com.microservice.users.contratacion.domain.models.TrabajoRealizado;
import com.microservice.users.contratacion.infraestructure.dto.TrabajoRealizadoRequestDTO;
import com.microservice.users.contratacion.infraestructure.dto.TrabajoRealizadoRequestUpdateDTO;
import com.microservice.users.contratacion.infraestructure.entities.TrabajoRealizadoEntity;
import com.microservice.users.domain.models.Gardener;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapping;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TrabajoRealizadoMapper {
    TrabajoRealizado trabajoRealizadoEntityToTrabajoRealizado(TrabajoRealizadoEntity trabajoRealizadoEntity);
    TrabajoRealizadoEntity trabajoRealizadoToTrabajoRealizadoEntity(TrabajoRealizado trabajoRealizado);

    @Mapping(target = "gardener", expression = "java(mapJardineroFromId(trabajoRealizadoRequestUpdateDTO.getJardineroId()))")
    TrabajoRealizado trabajoRealizadoRequestUpdateDTOtoTrabajoRealizado(TrabajoRealizadoRequestUpdateDTO trabajoRealizadoRequestUpdateDTO);

    @Mapping(target = "gardener", expression = "java(mapJardineroFromId(trabajoRealizadoRequestDTO.getJardineroId()))")
    TrabajoRealizado trabajoRealizadoRequestDTOtoTrabajoRealizado(TrabajoRealizadoRequestDTO trabajoRealizadoRequestDTO);

    // función auxiliar para convertir el ID a un objeto Gardener
    default Gardener mapJardineroFromId(Long jardineroId) {
        if (jardineroId == null) {
            return null;
        }
        // Crear un objeto Gardener con solo el ID
        Gardener jardinero = new Gardener(jardineroId, null, null, null);
        return jardinero;
    }

}
