package com.microservice.users.servicios.infraestructure.repositories;

import com.microservice.users.servicios.infraestructure.entities.TipoDeServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTipoDeServicioRepository extends JpaRepository<TipoDeServicioEntity,Long> {
}
