package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.VendedorDTO;
import com.cperalta.tienda.dto.VendedorResponseDTO;
import com.cperalta.tienda.dto.VendedorUpdateDTO;
import com.cperalta.tienda.entity.Vendedor;

import java.util.List;

public interface VendedorService {
    List<VendedorResponseDTO> getAll();
    VendedorResponseDTO getById(Long id);
    Vendedor create(VendedorDTO vendedorDTO);
    Vendedor update(Long id, VendedorUpdateDTO vendedorUpdateDTO);
    boolean delete(Long id);
}
