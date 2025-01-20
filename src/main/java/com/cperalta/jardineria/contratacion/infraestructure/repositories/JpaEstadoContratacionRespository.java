package com.cperalta.jardineria.contratacion.infraestructure.repositories;

import com.cperalta.jardineria.contratacion.infraestructure.entities.EstadoContratacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEstadoContratacionRespository extends JpaRepository<EstadoContratacionEntity, Long> {
}
