package com.cperalta.jardineria.servicios.infraestructure.controllers;

import com.cperalta.jardineria.servicios.application.services.ServicioService;
import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.infraestructure.dto.ServicioRequestDTO;
import com.cperalta.jardineria.servicios.infraestructure.dto.ServicioResponseDTO;
import com.cperalta.jardineria.servicios.infraestructure.dto.ServicioUpdateRequestDTO;
import com.cperalta.jardineria.servicios.infraestructure.mapper.ServicioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servicio")
@Tag(name = "API de Servicios", description = "CRUD de los servicios ofrecidos por los Jardineros")
@AllArgsConstructor
public class ServicioController {

    private final ServicioService servicioService;
    private final ServicioMapper servicioMapper;

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Servicio por ID",
            description = "Este endpoint retorna un Servicio específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<ServicioResponseDTO> getServicioById(@PathVariable("id") UUID id){
        return servicioService.getById(id)
                .map(servicio -> ResponseEntity.ok(servicioMapper.servicioToServicioResponseDTO(servicio)))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los Servicios",
            description = "Este endpoint retorna todos los Servicios existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<ServicioResponseDTO>> getAllServicios(){
        List<ServicioResponseDTO> serviciosResponseDTO = servicioService.getAll().stream()
                .map(servicioMapper::servicioToServicioResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviciosResponseDTO, HttpStatus.OK);
    }


    @PostMapping
    @Operation(
            summary = "Crear un Servicio",
            description = "Este endpoint es para crear un nuevo Servicio. Requiere token con permiso de Admin"
    )
    public ResponseEntity<ServicioResponseDTO> createServicio(@Validated @RequestBody ServicioRequestDTO servicioRequestDTO){
        Servicio servicio = servicioMapper.servicioRequestDTOtoServicio(servicioRequestDTO);
        Servicio servicioCreado = servicioService.create(servicio);
        ServicioResponseDTO servicioCreadoResponseDTO = servicioMapper.servicioToServicioResponseDTO(servicioCreado);
        return new ResponseEntity<>( servicioCreadoResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Servicio",
            description = "Este endpoint es para eliminar un Servicio existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> deleteServicio(@PathVariable("id") UUID id){
        return servicioService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Servicio existente",
            description = "Este endpoint es para Actualizar un Servicio existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<ServicioResponseDTO> updateServicio(@PathVariable("id") UUID id,
                                                                @Validated @RequestBody ServicioUpdateRequestDTO servicioRequestDTO){
        Servicio servicio = servicioMapper.servicioUpdateRequestDTOtoServicio(servicioRequestDTO);
        return servicioService.update(id, servicio)
                .map(servicioActualizado -> new ResponseEntity<>( servicioMapper.servicioToServicioResponseDTO(servicioActualizado) ,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
