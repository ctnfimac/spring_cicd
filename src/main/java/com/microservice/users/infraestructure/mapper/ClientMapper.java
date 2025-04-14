package com.microservice.users.infraestructure.mapper;

import com.microservice.users.domain.models.Client;
import com.microservice.users.domain.records.ClientRecord;
import com.microservice.users.infraestructure.dto.ClientRequestDTO;
import com.microservice.users.infraestructure.dto.ClientRequestUpdateDTO;
import com.microservice.users.infraestructure.dto.ClientResponseDTO;
import com.microservice.users.infraestructure.dto.registerclient.RegisterClientRequestDTO;
import com.microservice.users.infraestructure.dto.registerclient.RegisterClientResponseDTO;
import com.microservice.users.infraestructure.entities.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    @Mapping(source = "baseUser", target = "baseUser")
    ClientEntity clientToClientEntity(Client client);
    Client clientEntityToClient(ClientEntity clientEntity);

    @Mapping(source = "roleId", target = "baseUser.role.id")
    @Mapping(source = "statusId", target = "baseUser.status.id")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "name", target= "baseUser.name")
    @Mapping(source = "lastName", target= "baseUser.lastName")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "password", target= "baseUser.password")
    Client clientRequestDTOtoClient(ClientRequestDTO clientRequestDTO);

    @Mapping(source = "id", target= "id")
    @Mapping(source = "telephone", target= "telephone")
    @Mapping(source = "address", target= "address")
    @Mapping(source = "latitude", target= "latitude")
    @Mapping(source = "longitude", target= "longitude")
    @Mapping(source = "baseUser.email", target= "email")
    @Mapping(source = "baseUser.name", target= "name")
    @Mapping(source = "baseUser.lastName", target= "lastName")
    @Mapping(source = "baseUser.password", target= "password")
    @Mapping(source = "baseUser.role.description", target= "role")
    @Mapping(source = "baseUser.status.description", target= "status")
    ClientResponseDTO clientToClientResponseDTO(Client client);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "roleId", target = "baseUser.role.id")
    @Mapping(source = "statusId", target = "baseUser.status.id")
    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "name", target= "baseUser.name")
    @Mapping(source = "lastName", target= "baseUser.lastName")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "password", target= "baseUser.password")
    Client clientRequestUpdateDTOtoClient(ClientRequestUpdateDTO clientRequestUpdateDTO);


    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target= "lastName")
    @Mapping(source = "email", target= "email")
    @Mapping(source = "password", target= "password")
    @Mapping(target = "tokenActivation", ignore = true)
    ClientRecord registerClientRequestDTOtoClientRecord(RegisterClientRequestDTO registerClientRequestDTO);

    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "name", target= "baseUser.name")
    @Mapping(source = "lastName", target= "baseUser.lastName")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "password", target= "baseUser.password")
    @Mapping(source = "tokenActivation", target= "baseUser.tokenActivation")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "latitude", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    ClientEntity clientRecordToClientEntity(ClientRecord clientRecord);

    @Mapping(source = "telephone", target = "telephone")
    @Mapping(source = "address", target= "address")
    @Mapping(source = "baseUser.name", target = "name")
    @Mapping(source = "baseUser.lastName", target= "lastName")
    @Mapping(source = "baseUser.email", target= "email")
    RegisterClientResponseDTO clientToRegisterClientResponseDTO(Client client);

}
