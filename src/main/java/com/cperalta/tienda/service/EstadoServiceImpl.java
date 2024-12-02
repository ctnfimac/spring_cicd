package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.EstadoDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.respository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoServiceImpl implements EstadoService{

    @Autowired
    private final EstadoRepository estadoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Estado> getAll() {
        return estadoRepository.findAll();
    }

    @Override
    public Estado getById(Long id) {
        return estadoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("El Estado con el ID "+id+" no existe"));
    }

    @Override
    @Transactional
    public Estado create(EstadoDTO estadoDTO) {
        Estado estadoNuevo = null;
        Estado estadoMapper = modelMapper.map(estadoDTO, Estado.class);
        estadoNuevo = estadoRepository.save(estadoMapper);
        return estadoNuevo;
    }

    @Override
    @Transactional
    public Estado update(Long id, EstadoDTO estadoDTO) {
        Estado estadoExistente = this.getById(id);

        // Valido que la nueva descripción no cause conflicto de unicidad
        if (estadoDTO.getDescripcion() != null &&
                estadoRepository.getEstadoByDescripcion(estadoDTO.getDescripcion()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un Estado con la descripción: " + estadoDTO.getDescripcion());
        }

        // Actualizo los campos de Estado
        modelMapper.map(estadoDTO, estadoExistente);

        return estadoRepository.save(estadoExistente);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        boolean resultado = false;
        if(estadoRepository.existsById(id)){
            estadoRepository.deleteById(id);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public Estado getByDescripcion(String descripcion) {
        return estadoRepository.getEstadoByDescripcion(descripcion).orElseThrow(()->new IllegalArgumentException("El Estado con la descripción "+descripcion+" no existe"));
    }
}
