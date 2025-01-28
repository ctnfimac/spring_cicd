package com.cperalta.jardineria.contratacion.infraestructure.controllers;

import com.cperalta.jardineria.contratacion.application.services.TrabajoRealizadoService;
import com.cperalta.jardineria.contratacion.domain.models.TrabajoRealizado;
import com.cperalta.jardineria.contratacion.infraestructure.dto.TrabajoRealizadoRequestDTO;
import com.cperalta.jardineria.contratacion.infraestructure.dto.TrabajoRealizadoRequestUpdateDTO;
import com.cperalta.jardineria.contratacion.infraestructure.exceptions.TrabajoRealizadoException;
import com.cperalta.jardineria.contratacion.infraestructure.mapper.TrabajoRealizadoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajorealizado")
@Tag(name = "API de Trabajos Realizados", description = "CRUD de los distintos Trabajos Realizados por los Jardineros")
@AllArgsConstructor
public class TrabajoRealizadoController {

    private final TrabajoRealizadoService trabajoRealizadoService;
    private final TrabajoRealizadoMapper trabajoRealizadoMapper;

    @GetMapping
    @Operation(
            summary = "Obtener todos los Trabajos Realizados",
            description = "Este endpoint retorna todos los Trabajos Realizados existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<TrabajoRealizado>> findAll(){
        return new ResponseEntity<>(trabajoRealizadoService.getAll() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Trabajo Realizado por ID",
            description = "Este endpoint retorna un Trabajo Realizado específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<TrabajoRealizado> findById(@PathVariable("id") Long id){
        return trabajoRealizadoService.getById(id)
                .map( trabajoRealizado-> new ResponseEntity<>(trabajoRealizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(
            summary = "Crear un Trabajo Realizado nuevo",
            description = "Este endpoint es para crear un nuevo Trabajo Realizado. Requiere token con permiso de Admin"
    )
    public ResponseEntity<TrabajoRealizado> create(@Validated  @RequestBody TrabajoRealizadoRequestDTO trabajoRealizadoRequestDTO){
        TrabajoRealizado trabajoRealizado = trabajoRealizadoMapper.trabajoRealizadoRequestDTOtoTrabajoRealizado(trabajoRealizadoRequestDTO);
        TrabajoRealizado trabajoRealizadoCreado = trabajoRealizadoService.create(trabajoRealizado);

        if (trabajoRealizadoCreado == null) {
            throw new TrabajoRealizadoException("Lo siento, No se pudo crear el trabajo realizado.");
        }
        return new ResponseEntity<>(trabajoRealizadoCreado, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Trabajo Realizado",
            description = "Este endpoint es para eliminar un Trabajo Realizado existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return trabajoRealizadoService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Trabajo Realizado existente",
            description = "Este endpoint es para Actualizar un Trabajo Realizado existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<TrabajoRealizado> update(@PathVariable("id") Long id,
                                                   @Validated @RequestBody TrabajoRealizadoRequestUpdateDTO trabajoRealizadoRequestUpdateDTO){
        TrabajoRealizado trabajoRealizado = trabajoRealizadoMapper.trabajoRealizadoRequestUpdateDTOtoTrabajoRealizado(trabajoRealizadoRequestUpdateDTO);
        return trabajoRealizadoService.update(id, trabajoRealizado)
                .map( trabajoRealizadoActualizado -> new ResponseEntity<>(trabajoRealizadoActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
