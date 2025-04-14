package com.microservice.users.infraestructure.repositories;

import com.microservice.users.domain.models.Status;
import com.microservice.users.infraestructure.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaStatusRepository extends JpaRepository<StatusEntity, Long> {
    Optional<Status> getStatusEntityByDescription(String description);
    StatusEntity findStatusEntityByDescription(String description);
}
