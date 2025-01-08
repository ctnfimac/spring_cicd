package com.cperalta.jardineria.service;

import com.cperalta.jardineria.dto.CompradorDTO;
import com.cperalta.jardineria.dto.CompradorResponseDTO;
import com.cperalta.jardineria.dto.CompradorUpdateDTO;
import com.cperalta.jardineria.entity.Comprador;

import java.util.List;

public interface CompradorService {
    List<CompradorResponseDTO> getAll();
    CompradorResponseDTO getById(Long id);
    Comprador create(CompradorDTO compradorDTO);
    Comprador update(Long id, CompradorUpdateDTO compradorUpdateDTO);
    boolean delete(Long id);
}
