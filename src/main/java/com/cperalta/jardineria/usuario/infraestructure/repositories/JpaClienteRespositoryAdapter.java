package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.output.ClienteRepositoryPort;
import com.cperalta.jardineria.usuario.infraestructure.mapper.ClienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaClienteRespositoryAdapter implements ClienteRepositoryPort {

    private final JpaClienteRepository jpaClienteRepository;
    private final ClienteMapper clienteMapper;

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
        return null;
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
