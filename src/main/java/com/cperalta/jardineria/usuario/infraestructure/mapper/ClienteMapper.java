package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.entities.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    @Mapping(source = "persona", target = "persona")
    ClienteEntity clienteToClienteEntity(Cliente cliente);
    Cliente clienteEntityToCliente(ClienteEntity clienteEntity);

    @Mapping(source = "rolId", target = "persona.rol.id")
    @Mapping(source = "estadoId", target = "persona.estado.id")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "latitud", target = "latitud")
    @Mapping(source = "longitud", target = "longitud")
    @Mapping(source = "nombre", target= "persona.nombre")
    @Mapping(source = "apellido", target= "persona.apellido")
    @Mapping(source = "email", target= "persona.email")
    @Mapping(source = "contrasenia", target= "persona.contrasenia")
    Cliente clienteRequestDTOtoCliente(ClienteRequestDTO clienteRequestDTO);

    @Mapping(source = "id", target= "id")
    @Mapping(source = "telefono", target= "telefono")
    @Mapping(source = "direccion", target= "direccion")
    @Mapping(source = "latitud", target= "latitud")
    @Mapping(source = "longitud", target= "longitud")
    @Mapping(source = "persona.email", target= "email")
    @Mapping(source = "persona.nombre", target= "nombre")
    @Mapping(source = "persona.apellido", target= "apellido")
    @Mapping(source = "persona.contrasenia", target= "contrasenia")
    @Mapping(source = "persona.rol.descripcion", target= "rol")
    @Mapping(source = "persona.estado.descripcion", target= "estado")
    ClienteResponseDTO clienteToClienteResponseDTO(Cliente cliente);
}
