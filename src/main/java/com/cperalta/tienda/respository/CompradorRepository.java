package com.cperalta.tienda.respository;

import com.cperalta.tienda.entity.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<Comprador,Long> {
}
