package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.output.ClienteRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.entities.ClienteEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.EstadoEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.PersonaEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RolEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.ClienteNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.EstadoNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.RolNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaClienteRespositoryAdapter implements ClienteRepositoryPort {

    private final JpaClienteRepository jpaClienteRepository;
    private final JpaEstadoRepository jpaEstadoRepository;
    private final JpaRolRepository jpaRolRepository;

    private final ClienteMapper clienteMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<Cliente> getById(Long id) {
        return jpaClienteRepository.findById(id)
                .map(clienteMapper::clienteEntityToCliente);
    }

    @Override
    public List<Cliente> getAll() {
        return jpaClienteRepository.findAll().stream()
                .map(clienteMapper::clienteEntityToCliente)
                .collect(Collectors.toList());
    }

    @Override
    public Cliente create(Cliente cliente) {
        Long rolId = cliente.getPersona().getRol().getId();
        Long estadoId = cliente.getPersona().getEstado().getId();

        // verifico si existe el rol y el estado
        RolEntity rolEntity = jpaRolRepository.findById(rolId)
                .orElseThrow(() -> new RolNotFoundException("El Rol ingresado es inexistente."));

        EstadoEntity estadoEntity = jpaEstadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));

        ClienteEntity clienteEntity = clienteMapper.clienteToClienteEntity(cliente);
        String encryptedPassword = encryptPassword(clienteEntity.getPersona().getContrasenia());
        clienteEntity.getPersona().setContrasenia(encryptedPassword);

        PersonaEntity personaEntity = clienteEntity.getPersona();
        personaEntity.setEstado(estadoEntity);
        personaEntity.setRol(rolEntity);

        clienteEntity.setPersona(personaEntity);

        try{
            ClienteEntity clienteCreado = jpaClienteRepository.save(clienteEntity);
            return clienteMapper.clienteEntityToCliente(clienteCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Cliente ingresado ya existe.");
        }
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        ClienteEntity clienteActual = jpaClienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("El cliente que quiere modificar no existe"));

        clienteActual.setTelefono(cliente.getTelefono() != null ? cliente.getTelefono() : clienteActual.getTelefono());
        clienteActual.setDireccion(cliente.getDireccion() != null ? cliente.getDireccion() : clienteActual.getDireccion());

        clienteActual.getPersona().setNombre(cliente.getPersona().getNombre() != null ?
                        cliente.getPersona().getNombre() :
                        clienteActual.getPersona().getNombre()
                );

        PersonaEntity personaActual = clienteActual.getPersona();
        personaActual.setApellido(cliente.getPersona().getApellido() != null ?
                cliente.getPersona().getApellido() :
                personaActual.getApellido()
        );

        Long estadoId = cliente.getPersona().getEstado().getId();
        Long rolId = cliente.getPersona().getRol().getId();
        String email = cliente.getPersona().getEmail();
        String contrasenia = cliente.getPersona().getContrasenia();

        personaActual.setEmail(email != null ? email : personaActual.getEmail());
        if(contrasenia != null){
            String contraseniaEncriptada = encryptPassword(contrasenia);
            personaActual.setContrasenia(contraseniaEncriptada);
        }

        if(estadoId != null){
            EstadoEntity estadoEntity = jpaEstadoRepository.findById(estadoId)
                    .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));
            personaActual.setEstado(estadoEntity);
        }

        if(rolId != null){
            RolEntity rolEntity = jpaRolRepository.findById(rolId)
                    .orElseThrow(() -> new RolNotFoundException("El Rol ingresado es inexistente."));
            personaActual.setRol(rolEntity);
        }

        clienteActual.setPersona(personaActual);

        try{
            ClienteEntity clienteCreado = jpaClienteRepository.save(clienteActual);
            return clienteMapper.clienteEntityToCliente(clienteCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Cliente ya existe.");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(jpaClienteRepository.existsById(id)){
            jpaClienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
