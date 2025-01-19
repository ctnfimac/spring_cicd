package com.cperalta.jardineria.servicios.infraestructure.controllers;

import com.cperalta.jardineria.servicios.application.services.TipoDeServicioService;
import com.cperalta.jardineria.servicios.domain.models.Servicio;
import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.servicios.infraestructure.dto.TipoDeServicioDTO;
import com.cperalta.jardineria.servicios.infraestructure.dto.TipoDeServicioUpdateRequestDTO;
import com.cperalta.jardineria.servicios.infraestructure.mapper.TipoDeServicioMapper;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tipodeservicio")
@Tag(name = "API de Tipo de Servicios", description = "CRUD de los tipos de servicios del sistema")
@AllArgsConstructor
public class TipoDeServicioController {
    private final TipoDeServicioService tipoDeServicioService;
    private final TipoDeServicioMapper tipoDeServicioMapper;

    @GetMapping
    @Operation(
            summary = "Obtener todos los Tpos de Servicios",
            description = "Este endpoint retorna todos los Tipos de Servicios existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<TipoDeServicioDTO>> getAllTiposDeServicio(){
        List<TipoDeServicioDTO> tiposDeServicios = tipoDeServicioService.getAll().stream()
                .map(tipoDeServicioMapper::tipoDeServicioToTipoDeServicioDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tiposDeServicios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Tipo de Servicio por ID",
            description = "Este endpoint retorna un Tipo de Servicio específico dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<TipoDeServicioDTO> getTipoDeServicioById(@PathVariable("id") Long id){
        return tipoDeServicioService.getById(id)
                .map(tipoDeServicio -> ResponseEntity.ok(tipoDeServicioMapper.tipoDeServicioToTipoDeServicioDTO(tipoDeServicio)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(
            summary = "Crear un Tipo de Servicio",
            description = "Este endpoint es para crear un nuevo Tipo de Servicio. Requiere token con permiso de Admin"
    )
    public ResponseEntity<TipoDeServicioDTO> createTipoDeServicio(@Validated @RequestBody TipoDeServicioDTO tipoDeServicioDTO){
        TipoDeServicio tipoDeServicio = tipoDeServicioMapper.tipoDeServicioDTOtoTipoDeServicio(tipoDeServicioDTO);
        TipoDeServicio tipoDeServicioCreado = tipoDeServicioService.create(tipoDeServicio);
        TipoDeServicioDTO tipoDeServicioCreadoDTO = tipoDeServicioMapper.tipoDeServicioToTipoDeServicioDTO(tipoDeServicioCreado);
        return new ResponseEntity<>( tipoDeServicioCreadoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un Tipo de Servicio",
            description = "Este endpoint es para eliminar un Tipo de Servicio existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Void> deleteTipoDeServicio(@PathVariable("id") Long id){
        return tipoDeServicioService.delete(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un Tipo de Servicio existente",
            description = "Este endpoint es para Actualizar un Tipo de Servicio existente. Requiere token con permiso de Admin"
    )
    public ResponseEntity<TipoDeServicioDTO> updateTipoDeServicio(@PathVariable("id") Long id,
                                                                @Validated @RequestBody TipoDeServicioUpdateRequestDTO tipoDeServicioDTO){
        TipoDeServicio tipoDeServicio = tipoDeServicioMapper.tipodeServicioUpdateRequestDTOtoTipoDeServicio(tipoDeServicioDTO);
        return tipoDeServicioService.update(id, tipoDeServicio)
                .map(tipoDeServicioActualizado -> new ResponseEntity<>( tipoDeServicioMapper.tipoDeServicioToTipoDeServicioDTO(tipoDeServicioActualizado) ,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
