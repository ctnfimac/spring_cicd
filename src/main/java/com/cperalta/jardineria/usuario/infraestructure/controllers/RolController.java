package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.RolService;
import com.cperalta.jardineria.usuario.domain.models.Rol;
import com.cperalta.jardineria.usuario.infraestructure.dto.RolDTO;
import com.cperalta.jardineria.usuario.infraestructure.exceptions.ResourceNotFoundException;
import com.cperalta.jardineria.usuario.infraestructure.mapper.RolMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
@Tag(name = "API de Roles", description = "CRUD de los distintos Roles de los usuarios")
@AllArgsConstructor
public class RolController {

    private final RolService rolService;
    private final RolMapper rolMapper;

    @GetMapping
    @Operation(
            summary = "Obtener todos los Roles",
            description = "Este endpoint retorna todos los Roles existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Rol>> getAll(){
        return new ResponseEntity<>(rolService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Rol por ID",
            description = "Este endpoint retorna un Rol específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Rol> getById(@PathVariable("id") Long id){
        return rolService.getById(id)
                .map( rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + id + " no encontrado"));
    }

    @PostMapping
    @Operation(
            summary = "Crear un Rol",
            description = "Este endpoint es para crear un nuevo Rol. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Rol> create(@Validated @RequestBody RolDTO rolDTO){
        Rol rolCreado = rolService.create(rolMapper.rolDTOtoRol(rolDTO));
        return new ResponseEntity<>(rolCreado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Rol",
            description = "Este endpoint es para eliminar un Rol existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return (rolService.delete(id)) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Rol existente",
            description = "Este endpoint es para Actualizar un Rol existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Rol> update(@PathVariable("id") Long id,
                                      @Validated @RequestBody RolDTO rolDTO){
        Rol rol = rolMapper.rolDTOtoRol(rolDTO);
        return rolService.update(id, rol)
                .map( rolActualizado -> new ResponseEntity<>(rolActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
