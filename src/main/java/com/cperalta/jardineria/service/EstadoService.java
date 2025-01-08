package com.cperalta.jardineria.service;

import com.cperalta.jardineria.dto.EstadoDTO;
import com.cperalta.jardineria.entity.Estado;

import java.util.List;

public interface EstadoService {
    List<Estado> getAll();
    Estado getById(Long id);
    Estado create(EstadoDTO estadoDTO);
    Estado update(Long id, EstadoDTO estadoDTO);
    boolean delete(Long id);
    Estado getByDescripcion(String descripcion);
}
