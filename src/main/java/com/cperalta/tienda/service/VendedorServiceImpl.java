package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.PersonaUpdateDTO;
import com.cperalta.tienda.dto.VendedorDTO;
import com.cperalta.tienda.dto.VendedorResponseDTO;
import com.cperalta.tienda.dto.VendedorUpdateDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Persona;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.entity.Vendedor;
import com.cperalta.tienda.respository.VendedorRepository;
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
public class VendedorServiceImpl implements VendedorService{

    @Autowired
    private final VendedorRepository vendedorRepository;

    @Autowired
    private final RolService rolService;

    @Autowired
    private final EstadoService estadoService;

    @Autowired
    private final PersonaService personaService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<VendedorResponseDTO> getAll() {
        List<Vendedor> vendedores = vendedorRepository.findAll();

        return vendedores.stream()
                .map(vendedor -> new VendedorResponseDTO(
                        vendedor.getId(),
                        vendedor.getTelefono(),
                        vendedor.getEmail(),
                        vendedor.getPersona().getNombre(),
                        vendedor.getPersona().getApellido(),
                        vendedor.getPersona().getRol().getDescripcion(),
                        vendedor.getPersona().getEstado().getDescripcion()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public VendedorResponseDTO getById(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor no encontrado con ID: " + id));
        return new VendedorResponseDTO(
                vendedor.getId(),
                vendedor.getTelefono(),
                vendedor.getEmail(),
                vendedor.getPersona().getNombre(),
                vendedor.getPersona().getApellido(),
                vendedor.getPersona().getRol().getDescripcion(),
                vendedor.getPersona().getEstado().getDescripcion()
        );
    }

    @Override
    @Transactional
    public Vendedor create(VendedorDTO vendedorDTO) {
        Rol rol = rolService.getById(vendedorDTO.getRolId().longValue());
        Estado estado = estadoService.getById(vendedorDTO.getEstadoId().longValue());
        String contrasenia = passwordEncoder.encode(vendedorDTO.getContrasenia());

        // Creo el objeto de la Persona
        Persona persona = new Persona();
        persona.setNombre(vendedorDTO.getNombre());
        persona.setApellido(vendedorDTO.getApellido());
        persona.setContrasenia(contrasenia);
        persona.setRol(rol);
        persona.setEstado(estado);

        // Creo y guardo el vendedor
        Vendedor vendedor = new Vendedor();
        vendedor.setTelefono(vendedorDTO.getTelefono());
        vendedor.setEmail(vendedorDTO.getEmail());
        vendedor.setPersona(persona);


        return vendedorRepository.save(vendedor);
    }

    @Override
    public Vendedor update(Long id, VendedorUpdateDTO vendedorUpdateDTO) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor no encontrado con ID: " + id));
        Integer idPersona = vendedor.getPersona().getId();

        PersonaUpdateDTO personaUpdateDTO = new PersonaUpdateDTO();
        personaUpdateDTO.setNombre(vendedorUpdateDTO.getNombre());
        personaUpdateDTO.setApellido(vendedorUpdateDTO.getApellido());
        personaUpdateDTO.setContrasenia(vendedorUpdateDTO.getContrasenia());
        personaUpdateDTO.setRolId(vendedorUpdateDTO.getRolId());
        personaUpdateDTO.setEstadoId(vendedorUpdateDTO.getEstadoId());

        Persona personaModificada = personaService.update(idPersona.longValue(), personaUpdateDTO);

        vendedor.setEmail(vendedorUpdateDTO.getEmail() != null ? vendedorUpdateDTO.getEmail() : vendedor.getEmail());
        vendedor.setTelefono(vendedorUpdateDTO.getTelefono() != null ? vendedorUpdateDTO.getTelefono() : vendedor.getTelefono());
        return vendedorRepository.save(vendedor);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        boolean resultado = false;
        if(vendedorRepository.existsById(id)){
            Vendedor vendedor = vendedorRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Vendedor no encontrado con ID: " + id));
            Integer idPersona = vendedor.getPersona().getId();

            vendedorRepository.deleteById(id);
            personaService.delete(idPersona.longValue());
            resultado = true;
        }
        return resultado;
    }
}
