package com.cperalta.jardineria.usuario.infraestructure.repositories;

import com.cperalta.jardineria.usuario.infraestructure.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findClienteEntityByBaseUserEmailAndBaseUserTokenActivation(String email, String token);
}
