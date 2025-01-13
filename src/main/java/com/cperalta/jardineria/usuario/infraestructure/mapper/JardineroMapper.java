package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface JardineroMapper {
    JardineroEntity jardineroToJardineroEntity(Jardinero jardinero);
    Jardinero jardineroEntityToJardinero(JardineroEntity jardineroEntity);
}
