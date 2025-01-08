package com.cperalta.jardineria.service;

import com.cperalta.jardineria.dto.PersonaDTO;
import com.cperalta.jardineria.dto.PersonaUpdateDTO;
import com.cperalta.jardineria.entity.Persona;

import java.util.List;

public interface PersonaService {
    List<Persona> getAll();
    Persona getById(Long id);
    Persona create(PersonaDTO personaDTO);
    Persona update(Long id, PersonaUpdateDTO personaDTO);
    boolean delete(Long id);
}
