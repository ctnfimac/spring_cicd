package com.cperalta.tienda.respository;

import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.EstadoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstadoCompraRepository extends JpaRepository<EstadoCompra,Long> {
    Optional<EstadoCompra> findByDescripcion(String descripcion);

    @Query("SELECT e FROM EstadoCompra e WHERE LOWER(e.descripcion) = LOWER(:descripcion)")
    Optional<EstadoCompra> findByDescripcionIgnoreCase(@Param("descripcion") String descripcion);
}
