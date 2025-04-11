package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.output.ClienteRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.entities.ClienteEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.StatusEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.BaseUserEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RoleEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.ClienteNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.EstadoNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.RoleNotFoundException;
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
    private final JpaStatusRepository jpaStatusRepository;
    private final JpaRoleRepository jpaRoleRepository;

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
        Long roleId = cliente.getBaseUser().getRole().getId();
        Long estadoId = cliente.getBaseUser().getStatus().getId();

        // verifico si existe el rol y el estado
        RoleEntity roleEntity = jpaRoleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("El Rol ingresado es inexistente."));

        StatusEntity estadoEntity = jpaStatusRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));

        ClienteEntity clienteEntity = clienteMapper.clienteToClienteEntity(cliente);
        String encryptedPassword = encryptPassword(clienteEntity.getBaseUser().getPassword());
        clienteEntity.getBaseUser().setPassword(encryptedPassword);

        BaseUserEntity baseUserEntity = clienteEntity.getBaseUser();
        baseUserEntity.setStatus(estadoEntity);
        baseUserEntity.setRole(roleEntity);

        clienteEntity.setBaseUser(baseUserEntity);

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

        clienteActual.getBaseUser().setName(cliente.getBaseUser().getName() != null ?
                        cliente.getBaseUser().getName() :
                        clienteActual.getBaseUser().getName()
                );

        BaseUserEntity baseUserCurrent = clienteActual.getBaseUser();
        baseUserCurrent.setLastName(cliente.getBaseUser().getLastName() != null ?
                cliente.getBaseUser().getLastName() :
                baseUserCurrent.getLastName()
        );

        Long estadoId = cliente.getBaseUser().getStatus().getId();
        Long rolId = cliente.getBaseUser().getRole().getId();
        String email = cliente.getBaseUser().getEmail();
        String password = cliente.getBaseUser().getPassword();

        baseUserCurrent.setEmail(email != null ? email : baseUserCurrent.getEmail());
        if(password != null){
            String passwordEncrypted = encryptPassword(password);
            baseUserCurrent.setPassword(passwordEncrypted);
        }

        if(estadoId != null){
            StatusEntity estadoEntity = jpaStatusRepository.findById(estadoId)
                    .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));
            baseUserCurrent.setStatus(estadoEntity);
        }

        if(rolId != null){
            RoleEntity roleEntity = jpaRoleRepository.findById(rolId)
                    .orElseThrow(() -> new RoleNotFoundException("El Rol ingresado es inexistente."));
            baseUserCurrent.setRole(roleEntity);
        }

        clienteActual.setBaseUser(baseUserCurrent);

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
