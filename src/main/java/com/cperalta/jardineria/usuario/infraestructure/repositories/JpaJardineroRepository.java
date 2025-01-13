package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaJardineroRepository extends JpaRepository<JardineroEntity, Long> {
     Optional<JardineroEntity> findJardineroEntityByPersonaEmail(String email);
     Optional<JardineroEntity> findJardineroEntityByPersonaEmailAndPersonaContrasenia(String email, String contrasenia);
}
