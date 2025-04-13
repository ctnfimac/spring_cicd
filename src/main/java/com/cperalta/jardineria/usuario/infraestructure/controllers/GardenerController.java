package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.GardenerService;
import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.infraestructure.dto.GardenerRequestUpdateDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.GardenerRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.dto.GardenerResponseDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.GardenerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gardener")
@Tag(name = "API de Jardineros", description = "CRUD de los Jardineros del Sistema")
@AllArgsConstructor
public class GardenerController {

    private final GardenerService gardenerService;
    private final GardenerMapper gardenerMapper;

    
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Gardener por ID",
            description = "Este endpoint retorna un Gardener específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Gardener> getById(@PathVariable("id") Long id){
        return gardenerService.getById(id)
                .map( gardenerCreated -> ResponseEntity.ok(gardenerCreated))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping
    @Operation(
            summary = "Obtener todos los Jardineros",
            description = "Este endpoint retorna todos los Jardineros existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Gardener>> getAll(){
        return new ResponseEntity<>(gardenerService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Gardener",
            description = "Este endpoint es para eliminar un Gardener existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return gardenerService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    @Operation(
            summary = "Crear un Gardener",
            description = "Este endpoint es para crear un nuevo Gardener. Requiere token con permiso de Admin"
    )
    public ResponseEntity<GardenerResponseDTO> create(@Validated @RequestBody GardenerRequestDTO gardenerRequestDTO){
        Gardener gardener = gardenerMapper.gardenerRequestDTOtoGardener(gardenerRequestDTO);
        Gardener gardenerCreated = gardenerService.create(gardener);
        GardenerResponseDTO gardenerResponseDTO = gardenerMapper.gardenerToGardenerResponseDTO(gardenerCreated);
        return new ResponseEntity<>(gardenerResponseDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Gardener existente",
            description = "Este endpoint es para Actualizar un Gardener existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<GardenerResponseDTO> update(@PathVariable("id") Long id,
                                                       @RequestBody GardenerRequestUpdateDTO gardenerRequestUpdateDTO){
        Gardener gardener = gardenerMapper.gardenerRequestUpdateDTOtoGardener(gardenerRequestUpdateDTO);
        Gardener gardenerUpdated = gardenerService.update(id, gardener);
        GardenerResponseDTO gardenerResponseDTO = gardenerMapper.gardenerToGardenerResponseDTO(gardenerUpdated);
        return new ResponseEntity<>(gardenerResponseDTO, HttpStatus.OK);
    }

}
