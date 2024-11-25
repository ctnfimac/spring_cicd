package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.RolDTO;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RolServiceImpl implements RolService{

    @Autowired
    private final RolRepository rolRepository;

    @Override
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol getById(Long id) {
        return rolRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El rol con el ID " + id + " no existe."));
    }

    @Override
    public Rol create(RolDTO rolDTO) {
        return null;
    }

    @Override
    public Rol update(Long id, RolDTO rolDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
