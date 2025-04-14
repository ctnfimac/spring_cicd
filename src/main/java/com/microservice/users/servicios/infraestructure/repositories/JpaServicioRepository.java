package com.microservice.users.servicios.infraestructure.repositories;

import com.microservice.users.servicios.infraestructure.entities.ServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaServicioRepository extends JpaRepository<ServicioEntity, UUID> {
}
