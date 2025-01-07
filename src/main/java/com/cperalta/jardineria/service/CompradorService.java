package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.CompradorDTO;
import com.cperalta.tienda.dto.CompradorResponseDTO;
import com.cperalta.tienda.dto.CompradorUpdateDTO;
import com.cperalta.tienda.entity.Comprador;

import java.util.List;

public interface CompradorService {
    List<CompradorResponseDTO> getAll();
    CompradorResponseDTO getById(Long id);
    Comprador create(CompradorDTO compradorDTO);
    Comprador update(Long id, CompradorUpdateDTO compradorUpdateDTO);
    boolean delete(Long id);
}
