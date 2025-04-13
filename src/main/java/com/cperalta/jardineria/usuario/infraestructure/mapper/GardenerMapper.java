package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.records.GardenerRecord;
import com.cperalta.jardineria.usuario.infraestructure.dto.GardenerRequestUpdateDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.GardenerRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.GardenerResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegisterGardenerRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegisterGardenerResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.entities.GardenerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GardenerMapper {

    @Mapping(source = "telephone" , target = "telephone" )
    @Mapping(source = "presentation" , target = "presentation" )
    @Mapping(source = "baseUser.email" , target = "baseUser.email")
    @Mapping(source = "baseUser.name" , target = "baseUser.name")
    @Mapping(source = "baseUser.lastName" , target = "baseUser.lastName")
    @Mapping(source = "baseUser.password" , target = "baseUser.password")
    @Mapping(source = "baseUser.role" , target = "baseUser.role")
    @Mapping(source = "baseUser.status" , target = "baseUser.status")
    GardenerEntity gardenerToGardenerEntity(Gardener Gardener);

    Gardener gardenerEntityToGardener(GardenerEntity GardenerEntity);

    @Mapping(source = "telephone" , target = "telephone" )
    @Mapping(source = "email" , target = "baseUser.email")
    @Mapping(source = "presentation" , target = "presentation" )
    @Mapping(source = "name" , target = "baseUser.name")
    @Mapping(source = "lastName" , target = "baseUser.lastName")
    @Mapping(source = "password" , target = "baseUser.password")
    @Mapping(source = "roleId" , target = "baseUser.role.id")
    @Mapping(source = "statusId" , target = "baseUser.status.id")
    Gardener gardenerRequestDTOtoGardener(GardenerRequestDTO GardenerRequestDTO);

    @Mapping(source = "id", target= "id")
    @Mapping(source = "telephone", target= "telephone")
    @Mapping(source = "presentation", target= "presentation")
    @Mapping(source = "baseUser.email", target= "email")
    @Mapping(source = "baseUser.name", target= "name")
    @Mapping(source = "baseUser.lastName", target= "lastName")
    @Mapping(source = "baseUser.password", target= "password")
    @Mapping(source = "baseUser.role.description", target= "role")
    @Mapping(source = "baseUser.status.description", target= "status")
    GardenerResponseDTO gardenerToGardenerResponseDTO(Gardener Gardener);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "presentation", target = "presentation")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "name", target= "baseUser.name")
    @Mapping(source = "lastName", target= "baseUser.lastName")
    @Mapping(source = "password", target= "baseUser.password")
    @Mapping(source = "roleId", target = "baseUser.role.id")
    @Mapping(source = "statusId", target = "baseUser.status.id")
    Gardener gardenerRequestUpdateDTOtoGardener(GardenerRequestUpdateDTO gardenerRequestUpdateDTO);

    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target= "lastName")
    @Mapping(source = "email", target= "email")
    @Mapping(source = "password", target= "password")
    @Mapping(target = "tokenActivation", ignore = true)
    GardenerRecord registerGardenerRequestDTOtoGardenerRecord(RegisterGardenerRequestDTO registerGardenerRequestDTO);

    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "baseUser.name", target = "name")
    @Mapping(source = "baseUser.lastName", target= "lastName")
    @Mapping(source = "baseUser.email", target= "email")
    RegisterGardenerResponseDTO gardenerToRegisterGardenerResponseDTO(Gardener Gardener);

    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "name", target = "baseUser.name")
    @Mapping(source = "lastName", target= "baseUser.lastName")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "password", target= "baseUser.password")
    @Mapping(source = "tokenActivation", target= "baseUser.tokenActivation")
    @Mapping(target = "presentation", ignore = true)
    @Mapping(target = "id", ignore = true)
    GardenerEntity gardenerRecordToGardenerEntity(GardenerRecord GardenerRecord);
}
