package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.EstadoDTO;
import com.cperalta.tienda.entity.Estado;

import java.util.List;
import java.util.Optional;

public interface EstadoService {
    List<Estado> getAll();
    Estado getById(Long id);
    Estado create(EstadoDTO estadoDTO);
    Estado update(Long id, EstadoDTO estadoDTO);
    boolean delete(Long id);
    Estado getByDescripcion(String descripcion);
}
