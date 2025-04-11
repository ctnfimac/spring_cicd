package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.infraestructure.config.JwtUtil;
import com.cperalta.jardineria.usuario.infraestructure.entities.*;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.EstadoNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.JardineroNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.RoleNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import com.cperalta.jardineria.usuario.domain.ports.output.JardineroRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaJardineroRepositoryAdapter implements JardineroRepositoryPort {

    private final JpaJardineroRepository jpaJardineroRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaStatusRepository jpaStatusRepository;

    private final JardineroMapper jardineroMapper;
    private final JwtUtil jwtUtil;


    @Override
    public Optional<Jardinero> findByEmail(String email) {
        return jpaJardineroRepository.findJardineroEntityByBaseUserEmail(email).map(jardineroMapper::jardineroEntityToJardinero);
    }

    @Override
    public Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia) {
        return jpaJardineroRepository.findJardineroEntityByBaseUserEmailAndBaseUserPassword(email,contrasenia).map(jardineroMapper::jardineroEntityToJardinero);
    }

    @Override
    public Optional<Jardinero> getById(Long id) {
        return jpaJardineroRepository.findById(id).map(jardineroMapper::jardineroEntityToJardinero);
    }

    @Override
    public List<Jardinero> getAll() {
        return jpaJardineroRepository.findAll().stream()
                .map(jardineroMapper::jardineroEntityToJardinero)
                .collect(Collectors.toList());
    }

    @Override
    public Jardinero create(Jardinero jardinero) {
        Long rolId = jardinero.getBaseUser().getRole().getId();
        Long estadoId = jardinero.getBaseUser().getStatus().getId();

        // verifico si existe el rol y el estado
        RoleEntity roleEntity = jpaRoleRepository.findById(rolId)
                .orElseThrow(() -> new RoleNotFoundException("El Rol ingresado es inexistente."));

        StatusEntity estadoEntity = jpaStatusRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));

        // encripto la contraseña
        String encryptedPassword = jwtUtil.encryptPassword(jardinero.getBaseUser().getPassword());
        jardinero.getBaseUser().setPassword(encryptedPassword);

        JardineroEntity jardineroEntity = jardineroMapper.jardineroToJardineroEntity(jardinero);
        jardineroEntity.getBaseUser().setRole(roleEntity);
        jardineroEntity.getBaseUser().setStatus(estadoEntity);

        try {
            JardineroEntity jardineroEntityCreado = jpaJardineroRepository.save(jardineroEntity);
            return jardineroMapper.jardineroEntityToJardinero(jardineroEntityCreado);
        }catch (DataIntegrityViolationException ex) {
                throw new DuplicateResourceException("El Jardinero ingresado ya existe.");
        }
    }

    @Override
    public Jardinero update(Long id, Jardinero jardinero) {
        JardineroEntity jardineroActual = jpaJardineroRepository.findById(id)
                .orElseThrow( () -> new JardineroNotFoundException("El Jardinero que quiere modificar no existe"));

        jardineroActual.setTelefono(jardinero.getTelefono() != null ? jardinero.getTelefono() : jardineroActual.getTelefono());
        jardineroActual.setPresentacion(jardinero.getPresentacion() != null ? jardinero.getPresentacion() : jardineroActual.getPresentacion());

        BaseUserEntity baseUserCurrent = jardineroActual.getBaseUser();

        baseUserCurrent.setLastName(jardinero.getBaseUser().getLastName() != null ?
                jardinero.getBaseUser().getLastName() :
                baseUserCurrent.getLastName()
        );

        baseUserCurrent.setName(jardinero.getBaseUser().getName() != null ?
                jardinero.getBaseUser().getName() :
                baseUserCurrent.getName()
        );

        Long estadoId = jardinero.getBaseUser().getStatus().getId();
        Long roleId = jardinero.getBaseUser().getRole().getId();
        String email = jardinero.getBaseUser().getEmail();
        String contrasenia = jardinero.getBaseUser().getPassword();

        baseUserCurrent.setEmail(email != null ? email : baseUserCurrent.getEmail());
        if(contrasenia != null){
            String contraseniaEncriptada = jwtUtil.encryptPassword(contrasenia);
            baseUserCurrent.setPassword(contraseniaEncriptada);
        }

        if(estadoId != null){
            StatusEntity estadoEntity = jpaStatusRepository.findById(estadoId)
                    .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));
            baseUserCurrent.setStatus(estadoEntity);
        }

        if(roleId != null){
            RoleEntity roleEntity = jpaRoleRepository.findById(roleId)
                    .orElseThrow(() -> new RoleNotFoundException("El Rol ingresado es inexistente."));
            baseUserCurrent.setRole(roleEntity);
        }

        jardineroActual.setBaseUser(baseUserCurrent);

        try{
            JardineroEntity jardineroActualizado = jpaJardineroRepository.save(jardineroActual);
            return jardineroMapper.jardineroEntityToJardinero(jardineroActualizado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Jardinero ya existe.");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(jpaJardineroRepository.existsById(id)){
            jpaJardineroRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
