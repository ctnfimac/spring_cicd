package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.infraestructure.entities.GardenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaGardenerRepository extends JpaRepository<GardenerEntity, Long> {
     Optional<GardenerEntity> findGardenerEntityByBaseUserEmail(String email);
     Optional<GardenerEntity> findGardenerEntityByBaseUserEmailAndBaseUserPassword(String email, String password);
     GardenerEntity findGardenerEntityByBaseUserEmailAndBaseUserTokenActivation(String email, String token);
}
