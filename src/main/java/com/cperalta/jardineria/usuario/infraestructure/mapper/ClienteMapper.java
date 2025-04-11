package com.cperalta.jardineria.usuario.infraestructure.mapper;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.records.ClienteRecord;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteRequestUpdateDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.ClienteResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrocliente.RegistroClienteRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrocliente.RegistroClienteResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegistroJardineroRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.registrojardinero.RegistroJardineroResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.entities.ClienteEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    @Mapping(source = "baseUser", target = "baseUser")
    ClienteEntity clienteToClienteEntity(Cliente cliente);
    Cliente clienteEntityToCliente(ClienteEntity clienteEntity);

    @Mapping(source = "roleId", target = "baseUser.role.id")
    @Mapping(source = "statusId", target = "baseUser.status.id")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "latitud", target = "latitud")
    @Mapping(source = "longitud", target = "longitud")
    @Mapping(source = "name", target= "baseUser.name")
    @Mapping(source = "lastName", target= "baseUser.lastName")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "password", target= "baseUser.password")
    Cliente clienteRequestDTOtoCliente(ClienteRequestDTO clienteRequestDTO);

    @Mapping(source = "id", target= "id")
    @Mapping(source = "telefono", target= "telefono")
    @Mapping(source = "direccion", target= "direccion")
    @Mapping(source = "latitud", target= "latitud")
    @Mapping(source = "longitud", target= "longitud")
    @Mapping(source = "baseUser.email", target= "email")
    @Mapping(source = "baseUser.name", target= "nombre")
    @Mapping(source = "baseUser.lastName", target= "apellido")
    @Mapping(source = "baseUser.password", target= "contrasenia")
    @Mapping(source = "baseUser.role.description", target= "role")
    @Mapping(source = "baseUser.status.description", target= "status")
    ClienteResponseDTO clienteToClienteResponseDTO(Cliente cliente);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "roleId", target = "baseUser.role.id")
    @Mapping(source = "statusId", target = "baseUser.status.id")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "latitud", target = "latitud")
    @Mapping(source = "longitud", target = "longitud")
    @Mapping(source = "nombre", target= "baseUser.name")
    @Mapping(source = "apellido", target= "baseUser.lastName")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "contrasenia", target= "baseUser.password")
    Cliente clienteRequestUpdateDTOtoCliente(ClienteRequestUpdateDTO clienteRequestUpdateDTO);


    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target= "apellido")
    @Mapping(source = "email", target= "email")
    @Mapping(source = "contrasenia", target= "contrasenia")
    @Mapping(target = "tokenActivacion", ignore = true)
    ClienteRecord registroClienteRequestDTOtoClienteRecord(RegistroClienteRequestDTO registroClienteRequestDTO);

    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "nombre", target= "baseUser.name")
    @Mapping(source = "apellido", target= "baseUser.lastName")
    @Mapping(source = "email", target= "baseUser.email")
    @Mapping(source = "contrasenia", target= "baseUser.password")
    @Mapping(source = "tokenActivacion", target= "baseUser.tokenActivation")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "latitud", ignore = true)
    @Mapping(target = "longitud", ignore = true)
    ClienteEntity clienteRecordToJardineroEntity(ClienteRecord clienteRecord);

    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target= "direccion")
    @Mapping(source = "baseUser.name", target = "nombre")
    @Mapping(source = "baseUser.lastName", target= "apellido")
    @Mapping(source = "baseUser.email", target= "email")
    RegistroClienteResponseDTO clienteToRegistroClienteResponseDTO(Cliente cliente);

}
