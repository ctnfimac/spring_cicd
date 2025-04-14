package com.microservice.users.infraestructure.repositories;

import com.microservice.users.domain.ports.output.StatusRepositoryPort;
import com.microservice.users.infraestructure.entities.StatusEntity;
import com.microservice.users.infraestructure.mapper.StatusMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.microservice.users.domain.models.Status;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaStatusRepositoryAdapter implements StatusRepositoryPort {

    private final JpaStatusRepository jpaStatusRepository;
    private final StatusMapper statusMapper;

    @Override
    public Optional<Status> findById(Long id) {
        return jpaStatusRepository.findById(id).map(statusMapper::statusEntityToStatus);
    }

    @Override
    public List<Status> findAll() {
        return jpaStatusRepository.findAll().stream()
                .map(statusMapper::statusEntityToStatus)
                .collect(Collectors.toList());
    }

    @Override
    public Status create(Status status) {
        StatusEntity statusEntity = statusMapper.statusToStatusEntity(status);
        StatusEntity savedEstadoEntity = jpaStatusRepository.save(statusEntity);
        return statusMapper.statusEntityToStatus(savedEstadoEntity);
    }

    @Override
    public boolean delete(Long id) {
        if(jpaStatusRepository.existsById(id)) {
            jpaStatusRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Status> update(Long id, Status status) {
        if(jpaStatusRepository.existsById(id)){
            if (status.getDescription() != null &&
                    jpaStatusRepository.getStatusEntityByDescription(status.getDescription()).isPresent()) {
                throw new IllegalArgumentException("Ya existe un Status con la descripción: " + status.getDescription());
            }
            StatusEntity estadoEntityActual = jpaStatusRepository.getById(id);
            estadoEntityActual.setDescription(status.getDescription());
            StatusEntity estadoActualizado = jpaStatusRepository.save(estadoEntityActual);
            return Optional.of(statusMapper.statusEntityToStatus(estadoActualizado));
        }
        return Optional.empty();
    }
}
