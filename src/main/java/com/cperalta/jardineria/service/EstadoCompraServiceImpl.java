package com.cperalta.jardineria.service;

import com.cperalta.jardineria.dto.EstadoCompraDTO;
import com.cperalta.jardineria.entity.EstadoCompra;
import com.cperalta.jardineria.respository.EstadoCompraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoCompraServiceImpl implements EstadoCompraService{
    @Autowired
    private final EstadoCompraRepository estadoCompraRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<EstadoCompra> getAll() {
        return estadoCompraRepository.findAll();
    }

    @Override
    public EstadoCompraDTO getById(Long id) {
        EstadoCompra estadoCompra = estadoCompraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El Estado de Compra con el ID "+id+" no existe"));
        return new EstadoCompraDTO(
                estadoCompra.getDescripcion()
        );
    }

    @Override
    @Transactional
    public EstadoCompraDTO create(EstadoCompraDTO estadoCompraDTO) {
        EstadoCompra estadoCompra = new EstadoCompra();
        estadoCompra.setDescripcion(estadoCompraDTO.getDescripcion());

        EstadoCompra estadoCompraNuevo = estadoCompraRepository.save(estadoCompra);
        if(estadoCompraNuevo.getDescripcion() != null) return estadoCompraDTO;
        else return null;
    }

    @Override
    public EstadoCompraDTO update(Long id, EstadoCompraDTO estadoCompraDTO) {
        EstadoCompra estadoCompraExistente = estadoCompraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El Estado de Compra con el ID "+id+" no existe"));

        // Valido que la nueva descripción no cause conflicto de unicidad
        if (estadoCompraDTO.getDescripcion() != null &&
                estadoCompraRepository.findByDescripcion(estadoCompraDTO.getDescripcion()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un Estado de Compra con la descripción: " + estadoCompraDTO.getDescripcion());
        }

        // Actualizo los campos de Estado Compra
        modelMapper.map(estadoCompraDTO, estadoCompraExistente);

        // Guardo los cambios
        EstadoCompra estadoCompra = estadoCompraRepository.save(estadoCompraExistente);
        if(estadoCompra.getDescripcion() != null) return estadoCompraDTO;
        else return null;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        boolean resultado = false;
        if(estadoCompraRepository.existsById(id)){
            estadoCompraRepository.deleteById(id);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public EstadoCompra getByDescripcion(String descripcion) {
        return estadoCompraRepository.findByDescripcionIgnoreCase(descripcion)
                .orElseThrow(() -> new EntityNotFoundException("EstadoCompra no encontrado con descripción: " + descripcion));
    }


}
