package com.cperalta.jardineria.service;

import com.cperalta.jardineria.dto.VendedorDTO;
import com.cperalta.jardineria.dto.VendedorResponseDTO;
import com.cperalta.jardineria.dto.VendedorUpdateDTO;
import com.cperalta.jardineria.entity.Vendedor;

import java.util.List;

public interface VendedorService {
    List<VendedorResponseDTO> getAll();
    VendedorResponseDTO getById(Long id);
    Vendedor create(VendedorDTO vendedorDTO);
    Vendedor update(Long id, VendedorUpdateDTO vendedorUpdateDTO);
    boolean delete(Long id);
}
