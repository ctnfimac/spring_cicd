package com.cperalta.tienda.respository;

import com.cperalta.tienda.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
