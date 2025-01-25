package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.RolService;
import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.infraestructure.dto.RolDTO;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.ResourceNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.RolMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rol")
@AllArgsConstructor
public class RolController {

    private final RolService rolService;
    private final RolMapper rolMapper;

    @GetMapping
    public ResponseEntity<List<Rol>> getAll(){
        return new ResponseEntity<>(rolService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getById(@PathVariable("id") Long id){
        return rolService.getById(id)
                .map( rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + id + " no encontrado"));
    }

    @PostMapping
    public ResponseEntity<Rol> create(@Validated @RequestBody RolDTO rolDTO){
        Rol rolCreado = rolService.create(rolMapper.rolDTOtoRol(rolDTO));
        return new ResponseEntity<>(rolCreado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return (rolService.delete(id)) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable("id") Long id,
                                      @Validated @RequestBody RolDTO rolDTO){
        Rol rol = rolMapper.rolDTOtoRol(rolDTO);
        return rolService.update(id, rol)
                .map( rolActualizado -> new ResponseEntity<>(rolActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
