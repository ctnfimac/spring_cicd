package com.microservice.users.contratacion.infraestructure.controllers;

import com.microservice.users.contratacion.application.services.EstadoContratacionService;
import com.microservice.users.contratacion.domain.models.EstadoContratacion;
import com.microservice.users.contratacion.infraestructure.dto.EstadoContratacionRequestDTO;
import com.microservice.users.contratacion.infraestructure.mapper.EstadoContratacionMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadodecontratacion")
@Tag(name = "API de Estado de Contratación", description = "CRUD de los Estados de Contratación")
@AllArgsConstructor
public class EstadoContratacionController {

    private final EstadoContratacionService estadoContratacionService;
    private final EstadoContratacionMapper estadoContratacionMapper;

    @GetMapping
    @Operation(
            summary = "Obtener todos los Estados de Contratación",
            description = "Este endpoint retorna todos los Estados de Contratación existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<EstadoContratacion>> findAll(){
        return new ResponseEntity<>(estadoContratacionService.getAll() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Estado de Contratación por ID",
            description = "Este endpoint retorna un Estado de Contratación específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<EstadoContratacion> findById(@PathVariable("id") Long id){
        return estadoContratacionService.getById(id)
                .map( estadoContratacion -> new ResponseEntity<>(estadoContratacion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(
            summary = "Crear un Estado de Contratación nuevo",
            description = "Este endpoint es para crear un nuevo Estado de Contratación. Requiere token con permiso de Admin"
    )
    public ResponseEntity<EstadoContratacion> create(@Validated @RequestBody EstadoContratacionRequestDTO estadoContratacionRequestDTO){
        EstadoContratacion estadoContratacion = estadoContratacionMapper.estadoContratacionRequestDTOtoEstadoDeContratacion(estadoContratacionRequestDTO);
        return new ResponseEntity<>(estadoContratacionService.create(estadoContratacion), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")

    @Operation(
            summary = "Eliminar un Estado de Contratación",
            description = "Este endpoint es para eliminar un Estado de Contratación existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return estadoContratacionService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Estado de Contratación existente",
            description = "Este endpoint es para Actualizar un Estado de Contratación existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<EstadoContratacion> update(@PathVariable("id") Long id,
                                                   @Validated @RequestBody EstadoContratacionRequestDTO estadoContratacionRequestDTO){
        EstadoContratacion estadoContratacion = estadoContratacionMapper.estadoContratacionRequestDTOtoEstadoDeContratacion(estadoContratacionRequestDTO);
        return estadoContratacionService.update(id, estadoContratacion)
                .map( estadoContratacionActualizado -> new ResponseEntity<>(estadoContratacionActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
