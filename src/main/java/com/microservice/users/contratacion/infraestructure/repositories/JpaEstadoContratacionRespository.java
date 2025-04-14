package com.microservice.users.contratacion.infraestructure.repositories;

import com.microservice.users.contratacion.infraestructure.entities.EstadoContratacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEstadoContratacionRespository extends JpaRepository<EstadoContratacionEntity, Long> {
}
