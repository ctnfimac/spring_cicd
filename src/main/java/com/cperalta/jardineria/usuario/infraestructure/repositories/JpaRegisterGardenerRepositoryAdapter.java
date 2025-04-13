package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.constants.StatusEnum;
import com.cperalta.jardineria.usuario.domain.constants.RoleEnum;
import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.ports.output.RegisterGardenerRepositoryPort;
import com.cperalta.jardineria.usuario.domain.records.GardenerRecord;
import com.cperalta.jardineria.usuario.infraestructure.entities.StatusEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.GardenerEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RoleEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.GardenerMapper;
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
