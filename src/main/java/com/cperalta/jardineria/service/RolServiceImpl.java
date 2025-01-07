package com.cperalta.tienda.service;

import com.cperalta.tienda.dto.RolDTO;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.respository.RolRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class RolServiceImpl implements RolService{

    @Autowired
    private final RolRepository rolRepository;
    private final ModelMapper mapper;

    @Override
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol getById(Long id) {
        return rolRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El rol con el ID " + id + " no existe."));
    }

    @Override
    @Transactional
    public Rol create(RolDTO rolDTO) {
        Rol rolNuevo = null;
        Rol rol = mapper.map(rolDTO, Rol.class);
        rolNuevo = rolRepository.save(rol);
        return rolNuevo;
    }

    @Override
    public Rol update(Long id, RolDTO rolDTO) {
        Rol rolActualizado = null;
        if(rolRepository.existsById(id)) {
            Rol rolFromDB = this.getById(id);
            mapper.map(rolDTO, rolFromDB);
            rolFromDB.setDescripcion(rolDTO.getDescripcion());
            rolActualizado = rolRepository.save(rolFromDB);
        }
        return rolActualizado;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        boolean resultado = false;

        if(rolRepository.existsById(id)){
            Rol rolAeliminar = rolRepository.getById(id);
            rolRepository.delete(rolAeliminar);
            resultado = true;
        }
        return resultado;
    }
}
