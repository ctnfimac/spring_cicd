package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.domain.models.Persona;
import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.infraestructure.entities.EstadoEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.PersonaEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonaMapper {
    Persona personaEntityToPersona(PersonaEntity personaEntity);
    PersonaEntity personaToPersonaEntity(Persona persona);

    // Mapeos auxiliares para Rol y Estado
    /*Rol rolEntityToRol(RolEntity rolEntity);
    RolEntity rolToRolEntity(Rol rol);

    Estado estadoEntityToEstado(EstadoEntity estadoEntity);
    EstadoEntity estadoToEstadoEntity(Estado estado);*/
}
