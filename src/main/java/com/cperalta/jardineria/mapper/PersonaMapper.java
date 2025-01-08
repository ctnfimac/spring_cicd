package com.cperalta.jardineria.mapper;

import com.cperalta.jardineria.dto.PersonaDTO;
import com.cperalta.jardineria.entity.Estado;
import com.cperalta.jardineria.entity.Persona;
import com.cperalta.jardineria.entity.Rol;
import com.cperalta.jardineria.respository.EstadoRepository;
import com.cperalta.jardineria.respository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonaMapper {
    private final RolRepository rolRepository;
    private final EstadoRepository estadoRepository;

    public Persona toEntity(PersonaDTO personaDTO) {
        Rol rol = rolRepository.findById(personaDTO.getRolId().longValue())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + personaDTO.getRolId()));

        Estado estado = estadoRepository.findById(personaDTO.getEstadoId().longValue())
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado con ID: " + personaDTO.getEstadoId()));

        return Persona.builder()
                .nombre(personaDTO.getNombre())
                .apellido(personaDTO.getApellido())
                .contrasenia(personaDTO.getContrasenia())
                .rol(rol)
                .estado(estado)
                .build();
    }

}