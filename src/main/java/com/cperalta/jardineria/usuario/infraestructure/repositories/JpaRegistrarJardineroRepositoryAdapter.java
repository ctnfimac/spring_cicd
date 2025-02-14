package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.constants.EstadoEnum;
import com.cperalta.jardineria.usuario.domain.constants.RolEnum;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.output.RegistrarJardineroRepositoryPort;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;
import com.cperalta.jardineria.usuario.infraestructure.entities.EstadoEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RolEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JpaRegistrarJardineroRepositoryAdapter implements RegistrarJardineroRepositoryPort {

    private final JpaJardineroRepository jpaJardineroRepository;
    private final JpaRolRepository jpaRolRepository;
    private final JpaEstadoRepository jpaEstadoRepository;

    private final JardineroMapper jardineroMapper;

    @Override
    public Jardinero registrar(JardineroRecord jardineroRecord) {
        // transformar jardineroRecord a JardineroEntity
        JardineroEntity jardineroEntity = jardineroMapper.jardineroRecordToJardineroEntity(jardineroRecord);

        // Asignar el ROL y ESTADO correspondiente
        RolEntity rol = jpaRolRepository.findRolEntityByDescripcion(RolEnum.JARDINERO.toString());
        EstadoEntity estado = jpaEstadoRepository.findEstadoEntityByDescripcion(EstadoEnum.SIN_ACTIVAR.toString());

        jardineroEntity.getPersona().setEstado(estado);
        jardineroEntity.getPersona().setRol(rol);

        try{
            JardineroEntity jardineroCreado = jpaJardineroRepository.save(jardineroEntity);
            return jardineroMapper.jardineroEntityToJardinero(jardineroCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Jardinero ingresado ya existe.");
        }
    }
}
