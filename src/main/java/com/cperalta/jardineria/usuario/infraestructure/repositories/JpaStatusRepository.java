package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.infraestructure.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaStatusRepository extends JpaRepository<StatusEntity, Long> {
    Optional<Status> getStatusEntityByDescription(String description);
    StatusEntity findStatusEntityByDescription(String description);
}
