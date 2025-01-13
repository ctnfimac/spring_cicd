package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.entity.Persona;
import com.cperalta.jardineria.usuario.infraestructure.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByEmail(String email);
}
