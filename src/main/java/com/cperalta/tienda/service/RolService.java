package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.RolDTO;
import com.cperalta.tienda.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<Rol> getAll();
    Rol getById(Long id);
    Rol create(RolDTO rolDTO);
    Rol update(Long id, RolDTO rolDTO);
    boolean delete(Long id);
}
