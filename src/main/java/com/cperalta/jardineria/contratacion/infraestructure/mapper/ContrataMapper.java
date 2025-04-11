package com.cperalta.jardineria.contratacion.infraestructure.mapper;

import com.cperalta.jardineria.contratacion.domain.models.Contrata;
import com.cperalta.jardineria.contratacion.domain.models.EstadoContratacion;
import com.cperalta.jardineria.contratacion.infraestructure.dto.ContrataRequestDTO;
import com.cperalta.jardineria.contratacion.infraestructure.entities.ContrataEntity;
import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContrataMapper {
    Contrata contrataEntityToContrata(ContrataEntity contrataEntity);
    ContrataEntity contrataToContrataEntity(Contrata contrata);

    @Mapping(target = "jardinero", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "estadoContratacion", ignore = true)
    Contrata contrataRequestDTOtoContrata(ContrataRequestDTO contrataRequestDTO);

    /*default Jardinero mapJardineroFromId(Long jardineroId) {
        return (!(jardineroId == null)) ? new Jardinero(jardineroId, null, null, null) : null;
    }

    default Cliente mapClienteFromId(Long clienteId) {
        return (clienteId == null) ? null : new Cliente(clienteId, null, null, null, null, null);
    }

    default EstadoContratacion mapEstadpContratacionFromId(Long estadoContratacionId) {
        return (!(estadoContratacionId == null)) ?new EstadoContratacion(estadoContratacionId,null) : null;
    }*/
}
