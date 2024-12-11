package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.VendedorDTO;
import com.cperalta.tienda.dto.VendedorResponseDTO;
import com.cperalta.tienda.entity.Vendedor;
import com.cperalta.tienda.respository.VendedorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VendedorServiceImpl implements VendedorService{

    @Autowired
    private final VendedorRepository vendedorRepository;

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
    public Vendedor create(VendedorDTO vendedorDTO) {
        return null;
    }

    @Override
    public Vendedor update(Long id, VendedorDTO vendedorDTO) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
