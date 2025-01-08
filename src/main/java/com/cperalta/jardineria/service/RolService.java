package com.cperalta.jardineria.service;

import com.cperalta.jardineria.dto.RolDTO;
import com.cperalta.jardineria.entity.Rol;

import java.util.List;

public interface RolService {
    List<Rol> getAll();
    Rol getById(Long id);
    Rol create(RolDTO rolDTO);
    Rol update(Long id, RolDTO rolDTO);
    boolean delete(Long id);
}
