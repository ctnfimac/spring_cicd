package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.*;
import com.cperalta.tienda.entity.*;
import com.cperalta.tienda.respository.CompradorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompradorServiceImpl implements CompradorService{

    @Autowired
    private final CompradorRepository compradorRepository;

    @Autowired
    private final RolService rolService;

    @Autowired
    private final EstadoService estadoService;

    @Autowired
    private final PersonaService personaService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<CompradorResponseDTO> getAll() {
        List<Comprador> compradores = compradorRepository.findAll();

        return compradores.stream()
                .map(comprador -> new CompradorResponseDTO(
                        comprador.getId(),
                        comprador.getPersona().getNombre(),
                        comprador.getPersona().getApellido(),
                        comprador.getPersona().getRol().getDescripcion(),
                        comprador.getPersona().getEstado().getDescripcion(),
                        comprador.getTelefono(),
                        comprador.getDireccion(),
                        comprador.getEmail(),
                        comprador.getLatitud(),
                        comprador.getLongitud()
                ))
                .collect(Collectors.toList());
    }

    @Override // estado - telefono - nombre - apeliido
    public CompradorResponseDTO getById(Long id) {
        Comprador comprador = compradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comprador no encontrado con ID: " + id));
        return new CompradorResponseDTO(
                comprador.getId(),
                comprador.getPersona().getNombre(),
                comprador.getPersona().getApellido(),
                comprador.getPersona().getRol().getDescripcion(),
                comprador.getPersona().getEstado().getDescripcion(),
                comprador.getTelefono(),
                comprador.getDireccion(),
                comprador.getEmail(),
                null,
                null
                /*comprador.getTelefono(),
                comprador.getDireccion(),
                comprador.getEmail(),
                comprador.getLatitud(),
                comprador.getLongitud(),
                comprador.getPersona().getApellido(),
                comprador.getPersona().getRol().getDescripcion(),
                comprador.getPersona().getEstado().getDescripcion()*/
        );
    }

    @Override
    @Transactional
    public Comprador create(CompradorDTO compradorDTO) {
        Rol rol = rolService.getById(compradorDTO.getRolId().longValue());
        Estado estado = estadoService.getById(compradorDTO.getEstadoId().longValue());
        String contrasenia = passwordEncoder.encode(compradorDTO.getContrasenia());

        // Creo el objeto de la Persona
        Persona persona = new Persona();
        persona.setNombre(compradorDTO.getNombre());
        persona.setApellido(compradorDTO.getApellido());
        persona.setContrasenia(contrasenia);
        persona.setRol(rol);
        persona.setEstado(estado);

        // Creo y guardo el comprador
        Comprador comprador = new Comprador();
        comprador.setTelefono(compradorDTO.getTelefono());
        comprador.setDireccion(compradorDTO.getDireccion());
        comprador.setEmail(compradorDTO.getEmail());
        // TODO: Debo llamar a una api externa para completar la latitud y longitud
        comprador.setPersona(persona);

        return compradorRepository.save(comprador);
    }

    @Override
    public Comprador update(Long id, CompradorUpdateDTO compradorUpdateDTO) {
        Comprador comprador = compradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comprador no encontrado con ID: " + id));
        Integer idPersona = comprador.getPersona().getId();

        PersonaUpdateDTO personaUpdateDTO = new PersonaUpdateDTO();
        personaUpdateDTO.setNombre(compradorUpdateDTO.getNombre());
        personaUpdateDTO.setApellido(compradorUpdateDTO.getApellido());
        personaUpdateDTO.setContrasenia(compradorUpdateDTO.getContrasenia());
        personaUpdateDTO.setRolId(compradorUpdateDTO.getRolId());
        personaUpdateDTO.setEstadoId(compradorUpdateDTO.getEstadoId());

        Persona personaModificada = personaService.update(idPersona.longValue(), personaUpdateDTO);

        comprador.setEmail(compradorUpdateDTO.getEmail() != null ?
                compradorUpdateDTO.getEmail() :
                comprador.getEmail()
        );

        comprador.setTelefono(compradorUpdateDTO.getTelefono() != null ?
                compradorUpdateDTO.getTelefono() :
                comprador.getTelefono()
        );

        comprador.setDireccion(compradorUpdateDTO.getDireccion() != null ?
                compradorUpdateDTO.getDireccion() :
                comprador.getDireccion()
        );
        // TODO: modificar el long y latitud si se modifica la direccion

        return compradorRepository.save(comprador);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        boolean resultado = false;
        if(compradorRepository.existsById(id)){
            Comprador comprador = compradorRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Comprador no encontrado con ID: " + id));
            Integer idPersona = comprador.getPersona().getId();

            compradorRepository.deleteById(id);
            personaService.delete(idPersona.longValue());
            resultado = true;
        }
        return resultado;
    }
}
