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
    private final JpaEstadoRepository jpaEstadoRepository;

    private final JardineroMapper jardineroMapper;
    private final JwtUtil jwtUtil;


    @Override
    public Optional<Jardinero> findByEmail(String email) {
        return jpaJardineroRepository.findJardineroEntityByPersonaEmail(email).map(jardineroMapper::jardineroEntityToJardinero);
    }

    @Override
    public Optional<Jardinero> getByEmailAndContrasenia(String email, String contrasenia) {
        return jpaJardineroRepository.findJardineroEntityByPersonaEmailAndPersonaContrasenia(email,contrasenia).map(jardineroMapper::jardineroEntityToJardinero);
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
        Long rolId = jardinero.getPersona().getRole().getId();
        Long estadoId = jardinero.getPersona().getEstado().getId();

        // verifico si existe el rol y el estado
        RoleEntity roleEntity = jpaRoleRepository.findById(rolId)
                .orElseThrow(() -> new RoleNotFoundException("El Rol ingresado es inexistente."));

        EstadoEntity estadoEntity = jpaEstadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));

        // encripto la contraseña
        String encryptedPassword = jwtUtil.encryptPassword(jardinero.getPersona().getContrasenia());
        jardinero.getPersona().setContrasenia(encryptedPassword);

        JardineroEntity jardineroEntity = jardineroMapper.jardineroToJardineroEntity(jardinero);
        jardineroEntity.getPersona().setRole(roleEntity);
        jardineroEntity.getPersona().setEstado(estadoEntity);

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

        PersonaEntity personaActual = jardineroActual.getPersona();

        personaActual.setApellido(jardinero.getPersona().getApellido() != null ?
                jardinero.getPersona().getApellido() :
                personaActual.getApellido()
        );

        personaActual.setNombre(jardinero.getPersona().getNombre() != null ?
                jardinero.getPersona().getNombre() :
                personaActual.getNombre()
        );

        Long estadoId = jardinero.getPersona().getEstado().getId();
        Long roleId = jardinero.getPersona().getRole().getId();
        String email = jardinero.getPersona().getEmail();
        String contrasenia = jardinero.getPersona().getContrasenia();

        personaActual.setEmail(email != null ? email : personaActual.getEmail());
        if(contrasenia != null){
            String contraseniaEncriptada = jwtUtil.encryptPassword(contrasenia);
            personaActual.setContrasenia(contraseniaEncriptada);
        }

        if(estadoId != null){
            EstadoEntity estadoEntity = jpaEstadoRepository.findById(estadoId)
                    .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));
            personaActual.setEstado(estadoEntity);
        }

        if(roleId != null){
            RoleEntity roleEntity = jpaRoleRepository.findById(roleId)
                    .orElseThrow(() -> new RoleNotFoundException("El Rol ingresado es inexistente."));
            personaActual.setRole(roleEntity);
        }

        jardineroActual.setPersona(personaActual);

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
