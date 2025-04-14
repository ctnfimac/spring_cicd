package com.microservice.users.infraestructure.repositories;

import com.microservice.users.domain.constants.StatusEnum;
import com.microservice.users.domain.constants.RoleEnum;
import com.microservice.users.domain.models.Gardener;
import com.microservice.users.domain.ports.output.RegisterGardenerRepositoryPort;
import com.microservice.users.domain.records.GardenerRecord;
import com.microservice.users.infraestructure.entities.StatusEntity;
import com.microservice.users.infraestructure.entities.GardenerEntity;
import com.microservice.users.infraestructure.entities.RoleEntity;
import com.microservice.users.infraestructure.exceptions.DuplicateResourceException;
import com.microservice.users.infraestructure.mapper.GardenerMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JpaRegisterGardenerRepositoryAdapter implements RegisterGardenerRepositoryPort {

    private final JpaGardenerRepository jpaJardineroRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaStatusRepository jpaStatusRepository;

    private final GardenerMapper jardineroMapper;

    @Override
    public Gardener register(GardenerRecord jardineroRecord) {
        // transformar jardineroRecord a GardenerEntity
        GardenerEntity jardineroEntity = jardineroMapper.gardenerRecordToGardenerEntity(jardineroRecord);

        // Asignar el ROL y ESTADO correspondiente
        RoleEntity role = jpaRoleRepository.findRoleEntityByDescription(RoleEnum.JARDINERO.toString());
        StatusEntity status = jpaStatusRepository.findStatusEntityByDescription(StatusEnum.SIN_ACTIVAR.toString());

        jardineroEntity.getBaseUser().setStatus(status);
        jardineroEntity.getBaseUser().setRole(role);

        try{
            GardenerEntity jardineroCreado = jpaJardineroRepository.save(jardineroEntity);
            return jardineroMapper.gardenerEntityToGardener(jardineroCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Gardener ingresado ya existe.");
        }
    }

    @Override
    public Boolean activate(String email, String token) {
        GardenerEntity jardinero = jpaJardineroRepository.findGardenerEntityByBaseUserEmailAndBaseUserTokenActivation(email, token);
        if(jardinero != null){
            StatusEntity status = jpaStatusRepository.findStatusEntityByDescription(StatusEnum.ACTIVO.toString());
            jardinero.getBaseUser().setStatus(status);
            jardinero.getBaseUser().setTokenActivation(null);
            jpaJardineroRepository.save(jardinero);
            return true;
        }
        return false;
    }
}
