package com.microservice.users.contratacion.infraestructure.repositories;

import com.microservice.users.contratacion.infraestructure.entities.TrabajoRealizadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTrabajoRealizadoRepository extends JpaRepository<TrabajoRealizadoEntity, Long> {
}
