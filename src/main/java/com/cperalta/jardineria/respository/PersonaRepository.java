package com.cperalta.jardineria.respository;

import com.cperalta.jardineria.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
