package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.constants.StatusEnum;
import com.cperalta.jardineria.usuario.domain.constants.RoleEnum;
import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.ports.output.RegisterClientRepositoryPort;
import com.cperalta.jardineria.usuario.domain.records.ClientRecord;
import com.cperalta.jardineria.usuario.infraestructure.entities.ClientEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.StatusEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RoleEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JpaRegisterClientRepositoryAdapter implements RegisterClientRepositoryPort {

    private final JpaClientRepository jpaClientRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaStatusRepository jpaStatusRepository;

    private final ClientMapper clienteMapper;


    @Override
    public Client register(ClientRecord clienteRecord) {
        ClientEntity clienteEntity = clienteMapper.clientRecordToClientEntity(clienteRecord);

        RoleEntity role = jpaRoleRepository.findRoleEntityByDescription(RoleEnum.CLIENTE.toString());
        StatusEntity status = jpaStatusRepository.findStatusEntityByDescription(StatusEnum.SIN_ACTIVAR.toString());

        clienteEntity.getBaseUser().setStatus(status);
        clienteEntity.getBaseUser().setRole(role);

        try{
            ClientEntity clienteCreado = jpaClientRepository.save(clienteEntity);
            return clienteMapper.clientEntityToClient(clienteCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Cliente ingresado ya existe.");
        }
    }

    @Override
    public Boolean activate(String email, String token) {
        ClientEntity cliente = jpaClientRepository.findClientEntityByBaseUserEmailAndBaseUserTokenActivation(email, token);
        if(cliente != null){
            StatusEntity status = jpaStatusRepository.findStatusEntityByDescription(StatusEnum.ACTIVO.toString());
            cliente.getBaseUser().setStatus(status);
            cliente.getBaseUser().setTokenActivation(null);
            jpaClientRepository.save(cliente);
            return true;
        }
        return false;
    }
}
