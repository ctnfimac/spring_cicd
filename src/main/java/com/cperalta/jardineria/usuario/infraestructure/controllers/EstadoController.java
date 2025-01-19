package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.EstadoService;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.infraestructure.dto.EstadoRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.EstadoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estado")
@Tag(name = "API de Estados", description = "CRUD de los distintos estados de los usuarios")
@AllArgsConstructor
public class EstadoController {
    //@Autowired
    private final EstadoService estadoService;
    private final EstadoMapper estadoMapper;

    @GetMapping
    @Operation(
            summary = "Obtener todos los Estados",
            description = "Este endpoint retorna todos los Estados existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Estado>> getAllEstados(){
        List<Estado> estados = estadoService.getAllEstados();
        return new ResponseEntity<>(estados, HttpStatus.OK);
    }

    @GetMapping("/{idEstado}")
    @Operation(
            summary = "Obtener Estado por ID",
            description = "Este endpoint retorna un Estado específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Estado> getEstado(@PathVariable("idEstado") Long idEstado){
        return estadoService.getEstadoById(idEstado)
                .map( estado -> new ResponseEntity<>(estado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(
            summary = "Crear un Estado",
            description = "Este endpoint es para crear un nuevo Estado. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Estado> createEstado(@Validated @RequestBody EstadoRequestDTO estado){
        Estado estadoNuevo = estadoMapper.estadoRequestDTOtoEstado(estado);
        Estado createdEstado = estadoService.createEstado(estadoNuevo);
        return new ResponseEntity<>(createdEstado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEstado}")
    @Operation(
            summary = "Eliminar un Estado",
            description = "Este endpoint es para eliminar un Estado existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> deleteEstado(@PathVariable("idEstado") Long idEstado){
        return estadoService.deleteEstado(idEstado) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{idEstado}")
    @Operation(
            summary = "Actualizar un Estado existente",
            description = "Este endpoint es para Actualizar un Estado existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Estado> updateEstado(@PathVariable("idEstado") Long idEstado,
                                               @Validated @RequestBody EstadoRequestDTO estadoRequestDTO){
        Estado estado = estadoMapper.estadoRequestDTOtoEstado(estadoRequestDTO);
        return estadoService.updateEstado(idEstado, estado)
                .map( estadoActualizado -> new ResponseEntity<>(estadoActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
