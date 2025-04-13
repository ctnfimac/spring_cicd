package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.ports.output.ClientRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.entities.ClientEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.StatusEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.BaseUserEntity;
import com.cperalta.jardineria.usuario.infraestructure.entities.RoleEntity;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.ClientNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.DuplicateResourceException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.EstadoNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.RoleNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaClientRespositoryAdapter implements ClientRepositoryPort {

    private final JpaClientRepository jpaClientRepository;
    private final JpaStatusRepository jpaStatusRepository;
    private final JpaRoleRepository jpaRoleRepository;

    private final ClientMapper clienteMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<Client> getById(Long id) {
        return jpaClientRepository.findById(id)
                .map(clienteMapper::clientEntityToClient);
    }

    @Override
    public List<Client> getAll() {
        return jpaClientRepository.findAll().stream()
                .map(clienteMapper::clientEntityToClient)
                .collect(Collectors.toList());
    }

    @Override
    public Client create(Client cliente) {
        Long roleId = cliente.getBaseUser().getRole().getId();
        Long estadoId = cliente.getBaseUser().getStatus().getId();

        // verifico si existe el rol y el estado
        RoleEntity roleEntity = jpaRoleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("El Rol ingresado es inexistente."));

        StatusEntity estadoEntity = jpaStatusRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNotFoundException("El Estado ingresado es inexistente."));

        ClientEntity clienteEntity = clienteMapper.clientToClientEntity(cliente);
        String encryptedPassword = encryptPassword(clienteEntity.getBaseUser().getPassword());
        clienteEntity.getBaseUser().setPassword(encryptedPassword);

        BaseUserEntity baseUserEntity = clienteEntity.getBaseUser();
        baseUserEntity.setStatus(estadoEntity);
        baseUserEntity.setRole(roleEntity);

        clienteEntity.setBaseUser(baseUserEntity);

        try{
            ClientEntity clienteCreado = jpaClientRepository.save(clienteEntity);
            return clienteMapper.clientEntityToClient(clienteCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Client ingresado ya existe.");
        }
    }

    @Override
    public Client update(Long id, Client cliente) {
        ClientEntity clienteActual = jpaClientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("El cliente que quiere modificar no existe"));

        clienteActual.setTelephone(cliente.getTelephone() != null ? cliente.getTelephone() : clienteActual.getTelephone());
        clienteActual.setAddress(cliente.getAddress() != null ? cliente.getAddress() : clienteActual.getAddress());

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
            ClientEntity clienteCreado = jpaClientRepository.save(clienteActual);
            return clienteMapper.clientEntityToClient(clienteCreado);
        }catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("El Client ya existe.");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(jpaClientRepository.existsById(id)){
            jpaClientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
