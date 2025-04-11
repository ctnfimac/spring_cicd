package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.infraestructure.entities.JardineroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaJardineroRepository extends JpaRepository<JardineroEntity, Long> {
     Optional<JardineroEntity> findJardineroEntityByBaseUserEmail(String email);
     Optional<JardineroEntity> findJardineroEntityByBaseUserEmailAndBaseUserPassword(String email, String password);
     JardineroEntity findJardineroEntityByBaseUserEmailAndBaseUserTokenActivation(String email, String token);
}
