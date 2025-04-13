package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.infraestructure.config.JwtUtil;
import com.cperalta.jardineria.usuario.infraestructure.entities.*;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.EstadoNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.JardineroNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.RoleNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.GardenerMapper;
import com.cperalta.jardineria.usuario.domain.ports.output.GardenerRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaGardenerRepositoryAdapter implements GardenerRepositoryPort {

    private final JpaGardenerRepository jpaJardineroRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaStatusRepository jpaStatusRepository;

    private final GardenerMapper jardineroMapper;
    private final JwtUtil jwtUtil;


    @Override
    public Optional<Gardener> findByEmail(String email) {
        return jpaJardineroRepository.findGardenerEntityByBaseUserEmail(email).map(jardineroMapper::gardenerEntityToGardener);
    }

    @Override
    public Optional<Gardener> getByEmailAndPassword(String email, String contrasenia) {
        return jpaJardineroRepository.findGardenerEntityByBaseUserEmailAndBaseUserPassword(email,contrasenia).map(jardineroMapper::gardenerEntityToGardener);
    }

    @Override
    public Optional<Gardener> getById(Long id) {
        return jpaJardineroRepository.findById(id).map(jardineroMapper::gardenerEntityToGardener);
    }

    @Override
    public List<Gardener> getAll() {
        return jpaJardineroRepository.findAll().stream()
                .map(jardineroMapper::gardenerEntityToGardener)
                .collect(Collectors.toList());
    }

    @Override
    public Gardener create(Gardener jardinero) {
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

        GardenerEntity jardineroEntity = jardineroMapper.gardenerToGardenerEntity(jardinero);
        jardineroEntity.getBaseUser().setRole(roleEntity);
        jardineroEntity.getBaseUser().setStatus(estadoEntity);

        try {
            GardenerEntity jardineroEntityCreado = jpaJardineroRepository.save(jardineroEntity);
            return jardineroMapper.gardenerEntityToGardener(jardineroEntityCreado);
        }catch (DataIntegrityViolationException ex) {
                throw new DuplicateResourceException("El Gardener ingresado ya existe.");
        }
    }

    @Override
    public Gardener update(Long id, Gardener jardinero) {
        GardenerEntity jardineroActual = jpaJardineroRepository.findById(id)
                .orElseThrow( () -> new JardineroNotFoundException("El Gardener que quiere modificar no existe"));

        jardineroActual.setTelephone(jardinero.getTelephone() != null ? jardinero.getTelephone() : jardineroActual.getTelephone());
        jardineroActual.setPresentation(jardinero.getPresentation() != null ? jardinero.getPresentation() : jardineroActual.getPresentation());

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
            GardenerEntity jardineroActualizado = jpaJardineroRepository.save(jardineroActual);
            return jardineroMapper.gardenerEntityToGardener(jardineroActualizado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Gardener ya existe.");
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
