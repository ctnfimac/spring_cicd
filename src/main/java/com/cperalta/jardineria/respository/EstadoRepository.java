package com.cperalta.tienda.respository;

import com.cperalta.tienda.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
    Optional<Estado> getEstadoByDescripcion(String descripcion);
}
