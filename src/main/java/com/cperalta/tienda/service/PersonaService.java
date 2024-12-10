package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.PersonaDTO;
import com.cperalta.tienda.dto.PersonaUpdateDTO;
import com.cperalta.tienda.entity.Persona;

import java.util.List;

public interface PersonaService {
    List<Persona> getAll();
    Persona getById(Long id);
    Persona create(PersonaDTO personaDTO);
    Persona update(Long id, PersonaUpdateDTO personaDTO);
    boolean delete(Long id);
}
