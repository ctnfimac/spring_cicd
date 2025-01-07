package com.cperalta.tienda.mapper;

import com.cperalta.tienda.dto.PersonaDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Persona;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.EstadoRepository;
import com.cperalta.tienda.respository.RolRepository;
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