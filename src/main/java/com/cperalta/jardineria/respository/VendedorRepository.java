package com.cperalta.jardineria.respository;

import com.cperalta.jardineria.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {
}
