package com.cperalta.jardineria.contratacion.infraestructure.mapper;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;
import com.cperalta.jardineria.contratacion.infraestructure.dto.ContrataRequestDTO;
import com.cperalta.jardineria.contratacion.infraestructure.entities.ContrataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContrataMapper {
    Contrata contrataEntityToContrata(ContrataEntity contrataEntity);
    ContrataEntity contrataToContrataEntity(Contrata contrata);

    @Mapping(target = "gardener", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "estadoContratacion", ignore = true)
    Contrata contrataRequestDTOtoContrata(ContrataRequestDTO contrataRequestDTO);
}
