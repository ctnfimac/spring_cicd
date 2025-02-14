package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRegistrarJardineroRepository extends JpaRepository<JardineroEntity, Long> {
}
