package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.constants.StatusEnum;
import com.cperalta.jardineria.usuario.domain.constants.RoleEnum;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.output.RegistrarJardineroRepositoryPort;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;
import com.cperalta.jardineria.usuario.infraestructure.entities.StatusEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RoleEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JpaRegistrarJardineroRepositoryAdapter implements RegistrarJardineroRepositoryPort {

    private final JpaJardineroRepository jpaJardineroRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaStatusRepository jpaStatusRepository;

    private final JardineroMapper jardineroMapper;

    @Override
    public Jardinero registrar(JardineroRecord jardineroRecord) {
        // transformar jardineroRecord a JardineroEntity
        JardineroEntity jardineroEntity = jardineroMapper.jardineroRecordToJardineroEntity(jardineroRecord);

        // Asignar el ROL y ESTADO correspondiente
        RoleEntity role = jpaRoleRepository.findRoleEntityByDescription(RoleEnum.JARDINERO.toString());
        StatusEntity status = jpaStatusRepository.findStatusEntityByDescription(StatusEnum.SIN_ACTIVAR.toString());

        jardineroEntity.getPersona().setStatus(status);
        jardineroEntity.getPersona().setRole(role);

        try{
            JardineroEntity jardineroCreado = jpaJardineroRepository.save(jardineroEntity);
            return jardineroMapper.jardineroEntityToJardinero(jardineroCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Jardinero ingresado ya existe.");
        }
    }

    @Override
    public Boolean activar(String email, String token) {
        JardineroEntity jardinero = jpaJardineroRepository.findJardineroEntityByPersonaEmailAndPersonaTokenActivacion(email, token);
        if(jardinero != null){
            StatusEntity status = jpaStatusRepository.findStatusEntityByDescription(StatusEnum.ACTIVO.toString());
            jardinero.getPersona().setStatus(status);
            jardinero.getPersona().setTokenActivacion(null);
            jpaJardineroRepository.save(jardinero);
            return true;
        }
        return false;
    }
}
