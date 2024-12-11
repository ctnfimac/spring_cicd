package com.cperalta.tienda.respository;

import com.cperalta.tienda.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {
}
