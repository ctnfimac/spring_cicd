package com.cperalta.jardineria.contratacion.infraestructure.repositories;

import com.cperalta.jardineria.contratacion.infraestructure.entities.ContrataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaContrataRepository extends JpaRepository<ContrataEntity, Long> {
}
