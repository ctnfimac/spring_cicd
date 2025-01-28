package com.cperalta.jardineria.contratacion.infraestructure.controllers;

import com.cperalta.jardineria.contratacion.application.services.ContrataService;
import com.cperalta.jardineria.contratacion.domain.models.Contrata;
import com.cperalta.jardineria.contratacion.infraestructure.dto.ContrataRequestDTO;
import com.cperalta.jardineria.contratacion.infraestructure.mapper.ContrataMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrata")
@Tag(name = "API de Contrataciones", description = "CRUD de las Contrataciones de Jardineros por parte de los Clientes")
@AllArgsConstructor
public class ContrataController {

    private final ContrataService contrataService;
    private final ContrataMapper contrataMapper;


    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Contratación por ID",
            description = "Este endpoint retorna una Contratación específica dado su ID. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Contrata> getById(@PathVariable("id") Long id){
        return contrataService.getById(id)
                .map( contrata -> new ResponseEntity<>(contrata, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    @Operation(
            summary = "Obtener todas las Contrataciones",
            description = "Este endpoint retorna todos las Contrataciones existentes. Requiere token con permiso de Admin"
    )
    public ResponseEntity<List<Contrata>> getAll(){
        return new ResponseEntity<>(contrataService.getAll(), HttpStatus.OK);
    }


    @PostMapping
    @Operation(
            summary = "Crear una Contratación",
            description = "Este endpoint es para crear una nueva Contratación. Requiere token con permiso de Admin"
    )
    public ResponseEntity<Contrata> create(@Validated @RequestBody ContrataRequestDTO contrataRequestDTO){
        Contrata contrata = contrataMapper.contrataRequestDTOtoContrata(contrataRequestDTO);
        Contrata contratacionCreada = contrataService.create(contrata);
        if (contratacionCreada == null){
            System.out.println("Imprimir error");
        }
        return new ResponseEntity<>(contratacionCreada, HttpStatus.CREATED);
    }
}
