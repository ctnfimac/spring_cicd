package com.cperalta.jardineria.respository;

import com.cperalta.jardineria.entity.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<Comprador,Long> {
}
