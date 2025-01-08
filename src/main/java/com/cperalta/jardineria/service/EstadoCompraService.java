package com.cperalta.jardineria.service;

import com.cperalta.jardineria.dto.EstadoCompraDTO;
import com.cperalta.jardineria.entity.EstadoCompra;

import java.util.List;

public interface EstadoCompraService {
    List<EstadoCompra> getAll();
    EstadoCompraDTO getById(Long id);
    EstadoCompraDTO create(EstadoCompraDTO estadoCompraDTO);
    EstadoCompraDTO update(Long id, EstadoCompraDTO estadoCompraDTO);
    boolean delete(Long id);
    EstadoCompra getByDescripcion(String descripcion);
}
