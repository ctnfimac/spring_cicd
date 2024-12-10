package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.PersonaDTO;
import com.cperalta.tienda.dto.PersonaUpdateDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Persona;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.mapper.PersonaMapper;
import com.cperalta.tienda.respository.EstadoRepository;
import com.cperalta.tienda.respository.PersonaRepository;
import com.cperalta.tienda.respository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private final PersonaRepository personaRepository;

    @Autowired
    private final RolRepository rolRepository;

    @Autowired
    private final EstadoRepository estadoRepository;

    @Autowired
    private final PersonaMapper personaMapper;

    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<Persona> getAll() {
        return personaRepository.findAll();
    }

    @Override
    public Persona getById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("La Persona con el ID "+id+" no existe"));
    }

    @Override
    @Transactional
    public Persona create(PersonaDTO personaDTO) {
        String contraseniaEncriptada = this.encriptarContrasenia(personaDTO.getContrasenia());
        personaDTO.setContrasenia(contraseniaEncriptada);

        Persona persona = personaMapper.toEntity(personaDTO);
        return personaRepository.save(persona);
    }

    @Override
    public Persona update(Long id, PersonaUpdateDTO personaDTO) {
        Persona personaExistente = this.getById(id);

        personaExistente.setNombre(personaDTO.getNombre() != null? personaDTO.getNombre() : personaExistente.getNombre());
        personaExistente.setApellido(personaDTO.getApellido() != null? personaDTO.getApellido() : personaExistente.getApellido());
        personaExistente.setContrasenia(personaDTO.getContrasenia() != null?
                this.encriptarContrasenia(personaDTO.getContrasenia()) :
                personaExistente.getContrasenia());

        if (personaDTO.getRolId() != null) {
            Rol rol = rolRepository.findById(personaDTO.getRolId().longValue())
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + personaDTO.getRolId()));
            personaExistente.setRol(rol);
        }
        if (personaDTO.getEstadoId() != null) {
            Estado estado = estadoRepository.findById(personaDTO.getEstadoId().longValue())
                    .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado con ID: " + personaDTO.getEstadoId()));
            personaExistente.setEstado(estado);
        }
        return personaRepository.save(personaExistente);
    }

    @Override
    public boolean delete(Long id) {
        boolean resultado = false;
        if(personaRepository.existsById(id)){
            personaRepository.deleteById(id);
            resultado = true;
        }
        return resultado;
    }

    private String encriptarContrasenia(String contrasenia){
        return passwordEncoder.encode(contrasenia);
    }
}
