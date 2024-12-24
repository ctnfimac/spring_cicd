package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.EstadoCompraDTO;
import com.cperalta.tienda.entity.EstadoCompra;

import java.util.List;

public interface EstadoCompraService {
    List<EstadoCompra> getAll();
    EstadoCompraDTO getById(Long id);
    EstadoCompraDTO create(EstadoCompraDTO estadoCompraDTO);
    EstadoCompraDTO update(Long id, EstadoCompraDTO estadoCompraDTO);
    boolean delete(Long id);
    EstadoCompra getByDescripcion(String descripcion);
}
