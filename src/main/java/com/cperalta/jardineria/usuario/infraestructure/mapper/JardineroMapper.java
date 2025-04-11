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
    @Mapping(source = "baseUser.email" , target = "baseUser.email")
    @Mapping(source = "baseUser.name" , target = "baseUser.name")
    @Mapping(source = "baseUser.lastName" , target = "baseUser.lastName")
    @Mapping(source = "baseUser.password" , target = "baseUser.password")
    @Mapping(source = "baseUser.role" , target = "baseUser.role")
    @Mapping(source = "baseUser.status" , target = "baseUser.status")
    JardineroEntity jardineroToJardineroEntity(Jardinero jardinero);

    Jardinero jardineroEntityToJardinero(JardineroEntity jardineroEntity);

    @Mapping(source = "telefono" , target = "telefono" )
    @Mapping(source = "email" , target = "baseUser.email")
    @Mapping(source = "presentacion" , target = "presentacion" )
    @Mapping(source = "name" , target = "baseUser.name")
    @Mapping(source = "lastName" , target = "baseUser.lastName")
    @Mapping(source = "password" , target = "baseUser.password")
    @Mapping(source = "roleId" , target = "baseUser.role.id")
    @Mapping(source = "statusId" , target = "baseUser.status.id")
    Jardinero jardineroRequestDTOtoJardinero(JardineroRequestDTO jardineroRequestDTO);

    @Mapping(source = "id", target= "id")
    @Mapping(source = "telefono", target= "telefono")
    @Mapping(source = "presentacion", target= "presentacion")
    @Mapping(source = "baseUser.email", target= "email")
    @Mapping(source = "baseUser.name", target= "nombre")
    @Mapping(source = "baseUser.lastName", target= "apellido")
    @Mapping(source = "baseUser.password", target= "contrasenia")
    @Mapping(source = "baseUser.role.description", target= "role")
    @Mapping(source = "baseUser.status.description", target= "status")
    JardineroResponseDTO jardineroToJardineroResponseDTO(Jardinero jardinero);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "presentacion", target = "presentacion")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "nombre", target= "baseUser.name")
    @Mapping(source = "apellido", target= "baseUser.lastName")
    @Mapping(source = "contrasenia", target= "baseUser.password")
    @Mapping(source = "roleId", target = "baseUser.role.id")
    @Mapping(source = "statusId", target = "baseUser.status.id")
    Jardinero jardineroRequestUpdateDTOtoJardinero(JardineroRequestUpdateDTO jardineroRequestUpdateDTO);

    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target= "apellido")
    @Mapping(source = "email", target= "email")
    @Mapping(source = "contrasenia", target= "contrasenia")
    @Mapping(target = "tokenActivacion", ignore = true)
    JardineroRecord registroJardineroRequestDTOtoJardineroRecord(RegistroJardineroRequestDTO registroJardineroRequestDTO);

    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "baseUser.name", target = "nombre")
    @Mapping(source = "baseUser.lastName", target= "apellido")
    @Mapping(source = "baseUser.email", target= "email")
    RegistroJardineroResponseDTO jardineroToRegistroJardineroResponseDTO(Jardinero jardinero);

    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "nombre", target = "baseUser.name")
    @Mapping(source = "apellido", target= "baseUser.lastName")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "contrasenia", target= "baseUser.password")
    @Mapping(source = "tokenActivacion", target= "baseUser.tokenActivation")
    @Mapping(target = "presentacion", ignore = true)
    @Mapping(target = "id", ignore = true)
    JardineroEntity jardineroRecordToJardineroEntity(JardineroRecord jardineroRecord);
}
