package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroRequestUpdateDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegistroJardineroRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegistroJardineroResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface JardineroMapper {

    @Mapping(source = "telefono" , target = "telefono" )
    @Mapping(source = "presentacion" , target = "presentacion" )
    @Mapping(source = "persona.email" , target = "persona.email")
    @Mapping(source = "persona.nombre" , target = "persona.nombre")
    @Mapping(source = "persona.apellido" , target = "persona.apellido")
    @Mapping(source = "persona.contrasenia" , target = "persona.contrasenia")
    @Mapping(source = "persona.rol" , target = "persona.rol")
    @Mapping(source = "persona.estado" , target = "persona.estado")
    JardineroEntity jardineroToJardineroEntity(Jardinero jardinero);

    Jardinero jardineroEntityToJardinero(JardineroEntity jardineroEntity);

    @Mapping(source = "telefono" , target = "telefono" )
    @Mapping(source = "email" , target = "persona.email")
    @Mapping(source = "presentacion" , target = "presentacion" )
    @Mapping(source = "nombre" , target = "persona.nombre")
    @Mapping(source = "apellido" , target = "persona.apellido")
    @Mapping(source = "contrasenia" , target = "persona.contrasenia")
    @Mapping(source = "rolId" , target = "persona.rol.id")
    @Mapping(source = "estadoId" , target = "persona.estado.id")
    Jardinero jardineroRequestDTOtoJardinero(JardineroRequestDTO jardineroRequestDTO);

    @Mapping(source = "id", target= "id")
    @Mapping(source = "telefono", target= "telefono")
    @Mapping(source = "presentacion", target= "presentacion")
    @Mapping(source = "persona.email", target= "email")
    @Mapping(source = "persona.nombre", target= "nombre")
    @Mapping(source = "persona.apellido", target= "apellido")
    @Mapping(source = "persona.contrasenia", target= "contrasenia")
    @Mapping(source = "persona.rol.descripcion", target= "rol")
    @Mapping(source = "persona.estado.descripcion", target= "estado")
    JardineroResponseDTO jardineroToJardineroResponseDTO(Jardinero jardinero);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "presentacion", target = "presentacion")
    @Mapping(source = "email", target= "persona.email")
    @Mapping(source = "nombre", target= "persona.nombre")
    @Mapping(source = "apellido", target= "persona.apellido")
    @Mapping(source = "contrasenia", target= "persona.contrasenia")
    @Mapping(source = "rolId", target = "persona.rol.id")
    @Mapping(source = "estadoId", target = "persona.estado.id")
    Jardinero jardineroRequestUpdateDTOtoJardinero(JardineroRequestUpdateDTO jardineroRequestUpdateDTO);

    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target= "apellido")
    @Mapping(source = "email", target= "email")
    @Mapping(source = "contrasenia", target= "contrasenia")
    JardineroRecord registroJardineroRequestDTOtoJardineroRecord(RegistroJardineroRequestDTO registroJardineroRequestDTO);

    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "persona.nombre", target = "nombre")
    @Mapping(source = "persona.apellido", target= "apellido")
    @Mapping(source = "persona.email", target= "email")
    RegistroJardineroResponseDTO jardineroToRegistroJardineroResponseDTO(Jardinero jardinero);

    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "nombre", target = "persona.nombre")
    @Mapping(source = "apellido", target= "persona.apellido")
    @Mapping(source = "email", target= "persona.email")
    @Mapping(source = "contrasenia", target= "persona.contrasenia")
    @Mapping(source = "tokenActivacion", target= "persona.tokenActivacion")
    JardineroEntity jardineroRecordToJardineroEntity(JardineroRecord jardineroRecord);
}
