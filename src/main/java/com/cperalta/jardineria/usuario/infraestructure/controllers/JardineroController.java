package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.JardineroService;
import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroRequestUpdateDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.JardineroResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.JardineroMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jardinero")
@Tag(name = "API de Jardineros", description = "CRUD de los Jardineros del Sistema")
@AllArgsConstructor
public class JardineroController {

    private final JardineroService jardineroService;
    private final JardineroMapper jardineroMapper;

    
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Jardinero por ID",
            description = "Este endpoint retorna un Jardinero específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Jardinero> getById(@PathVariable("id") Long id){
        return jardineroService.getById(id)
                .map( jardineroCreado -> ResponseEntity.ok(jardineroCreado))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping
    @Operation(
            summary = "Obtener todos los Jardineros",
            description = "Este endpoint retorna todos los Jardineros existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Jardinero>> getAll(){
        return new ResponseEntity<>(jardineroService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Jardinero",
            description = "Este endpoint es para eliminar un Jardinero existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return jardineroService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    @Operation(
            summary = "Crear un Jardinero",
            description = "Este endpoint es para crear un nuevo Jardinero. Requiere token con permiso de Admin"
    )
    public ResponseEntity<JardineroResponseDTO> create(@Validated @RequestBody JardineroRequestDTO jardineroRequestDTO){
        Jardinero jardinero = jardineroMapper.jardineroRequestDTOtoJardinero(jardineroRequestDTO);
        Jardinero jardineroCreado = jardineroService.create(jardinero);
        JardineroResponseDTO jardineroResponseDTO = jardineroMapper.jardineroToJardineroResponseDTO(jardineroCreado);
        return new ResponseEntity<>(jardineroResponseDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Jardinero existente",
            description = "Este endpoint es para Actualizar un Jardinero existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<JardineroResponseDTO> update(@PathVariable("id") Long id,
                                                       @RequestBody JardineroRequestUpdateDTO jardineroRequestUpdateDTO){
        Jardinero jardinero = jardineroMapper.jardineroRequestUpdateDTOtoJardinero(jardineroRequestUpdateDTO);
        Jardinero jardineroActualizado = jardineroService.update(id, jardinero);
        JardineroResponseDTO jardineroResponseDTO = jardineroMapper.jardineroToJardineroResponseDTO(jardineroActualizado);
        return new ResponseEntity<>(jardineroResponseDTO, HttpStatus.OK);
    }

}
