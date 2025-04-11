package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.constants.StatusEnum;
import com.cperalta.jardineria.usuario.domain.constants.RoleEnum;
import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.output.RegistrarClienteRepositoryPort;
import com.cperalta.jardineria.usuario.domain.records.ClienteRecord;
import com.cperalta.jardineria.usuario.infraestructure.entities.ClienteEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.StatusEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RoleEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JpaRegistrarClienteRepositoryAdapter implements RegistrarClienteRepositoryPort {

    private final JpaClienteRepository jpaClienteRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaStatusRepository jpaStatusRepository;

    private final ClienteMapper clienteMapper;


    @Override
    public Cliente registrar(ClienteRecord clienteRecord) {
        ClienteEntity clienteEntity = clienteMapper.clienteRecordToJardineroEntity(clienteRecord);

        RoleEntity role = jpaRoleRepository.findRoleEntityByDescription(RoleEnum.CLIENTE.toString());
        StatusEntity status = jpaStatusRepository.findStatusEntityByDescription(StatusEnum.SIN_ACTIVAR.toString());

        clienteEntity.getPersona().setStatus(status);
        clienteEntity.getPersona().setRole(role);

        try{
            ClienteEntity clienteCreado = jpaClienteRepository.save(clienteEntity);
            return clienteMapper.clienteEntityToCliente(clienteCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Cliente ingresado ya existe.");
        }
    }

    @Override
    public Boolean activar(String email, String token) {
        ClienteEntity cliente = jpaClienteRepository.findClienteEntityByPersonaEmailAndPersonaTokenActivacion(email, token);
        if(cliente != null){
            StatusEntity status = jpaStatusRepository.findStatusEntityByDescription(StatusEnum.ACTIVO.toString());
            cliente.getPersona().setStatus(status);
            cliente.getPersona().setTokenActivacion(null);
            jpaClienteRepository.save(cliente);
            return true;
        }
        return false;
    }
}
