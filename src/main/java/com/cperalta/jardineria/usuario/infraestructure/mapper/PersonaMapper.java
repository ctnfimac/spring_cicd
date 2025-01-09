package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.infraestructure.entities.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonaMapper {
    Persona personaEntityToPersona(PersonaEntity personaEntity);
    PersonaEntity personaToPersonaEntity(Persona persona);
}
