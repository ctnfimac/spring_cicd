package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.infraestructure.entities.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaEstadoRepository extends JpaRepository<EstadoEntity, Long> {
    Optional<Estado> getEstadoByDescripcion(String descripcion);
    EstadoEntity findEstadoEntityByDescripcion(String descripcion);
}
