package com.microservice.users.infraestructure.controllers;

import com.microservice.users.application.services.RoleService;
import com.microservice.users.domain.models.Role;
import com.microservice.users.infraestructure.dto.RoleDTO;
import com.microservice.users.infraestructure.exceptions.ResourceNotFoundException;
import com.microservice.users.infraestructure.mapper.RoleMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@Tag(name = "API de Roles", description = "CRUD de los distintos Roles de los usuarios")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping
    @Operation(
            summary = "Obtener todos los Roles",
            description = "Este endpoint retorna todos los Roles existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Role>> getAll(){
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Rol por ID",
            description = "Este endpoint retorna un Rol específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Role> getById(@PathVariable("id") Long id){
        return roleService.getById(id)
                .map( rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + id + " no encontrado"));
    }

    @PostMapping
    @Operation(
            summary = "Crear un Rol",
            description = "Este endpoint es para crear un nuevo Rol. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Role> create(@Validated @RequestBody RoleDTO roleDTO){
        Role roleCreated = roleService.create(roleMapper.roleDTOtoRole(roleDTO));
        return new ResponseEntity<>(roleCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Rol",
            description = "Este endpoint es para eliminar un Rol existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return (roleService.delete(id)) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Rol existente",
            description = "Este endpoint es para Actualizar un Rol existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Role> update(@PathVariable("id") Long id,
                                      @Validated @RequestBody RoleDTO roleDTO){
        Role role = roleMapper.roleDTOtoRole(roleDTO);
        return roleService.update(id, role)
                .map( rolActualizado -> new ResponseEntity<>(rolActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
