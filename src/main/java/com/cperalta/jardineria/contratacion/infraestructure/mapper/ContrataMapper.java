package com.cperalta.jardineria.contratacion.infraestructure.mapper;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;
import com.cperalta.jardineria.contratacion.infraestructure.entities.ContrataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContrataMapper {
    Contrata contrataEntityToContrata(ContrataEntity contrataEntity);
    ContrataEntity contrataToContrataEntity(Contrata contrata);
}
