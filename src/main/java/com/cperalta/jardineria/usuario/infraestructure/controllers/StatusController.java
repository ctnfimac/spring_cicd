package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.StatusService;
import com.cperalta.jardineria.usuario.domain.models.Status;
import com.cperalta.jardineria.usuario.infraestructure.dto.StatusRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.StatusMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
@Tag(name = "API de Estados", description = "CRUD de los distintos estados de los usuarios")
@AllArgsConstructor
public class StatusController {
    //@Autowired
    private final StatusService statusService;
    private final StatusMapper statusMapper;

    @GetMapping
    @Operation(
            summary = "Obtener todos los Estados",
            description = "Este endpoint retorna todos los Estados existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Status>> getAllStatus(){
        List<Status> status = statusService.getAll();
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/{idEstado}")
    @Operation(
            summary = "Obtener Status por ID",
            description = "Este endpoint retorna un Status específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Status> getStatus(@PathVariable("idEstado") Long idEstado){
        return statusService.getById(idEstado)
                .map( estado -> new ResponseEntity<>(estado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(
            summary = "Crear un Status",
            description = "Este endpoint es para crear un nuevo Status. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Status> createEstado(@Validated @RequestBody StatusRequestDTO estado){
        Status estadoNuevo = statusMapper.statusRequestDTOtoStatus(estado);
        Status createdEstado = statusService.create(estadoNuevo);
        return new ResponseEntity<>(createdEstado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEstado}")
    @Operation(
            summary = "Eliminar un Status",
            description = "Este endpoint es para eliminar un Status existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> deleteEstado(@PathVariable("idEstado") Long idEstado){
        return statusService.delete(idEstado) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{idEstado}")
    @Operation(
            summary = "Actualizar un Status existente",
            description = "Este endpoint es para Actualizar un Status existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Status> updateEstado(@PathVariable("idEstado") Long idEstado,
                                               @Validated @RequestBody StatusRequestDTO estadoRequestDTO){
        Status estado = statusMapper.statusRequestDTOtoStatus(estadoRequestDTO);
        return statusService.update(idEstado, estado)
                .map( estadoActualizado -> new ResponseEntity<>(estadoActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
