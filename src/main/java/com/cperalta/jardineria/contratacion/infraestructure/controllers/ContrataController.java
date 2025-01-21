package com.cperalta.jardineria.contratacion.infraestructure.controllers;

import com.cperalta.jardineria.contratacion.application.services.ContrataService;
import com.cperalta.jardineria.contratacion.domain.models.Contrata;
import com.cperalta.jardineria.contratacion.infraestructure.dto.ContrataRequestDTO;
import com.cperalta.jardineria.contratacion.infraestructure.mapper.ContrataMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrata")
@AllArgsConstructor
public class ContrataController {

    private final ContrataService contrataService;
    private final ContrataMapper contrataMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Contrata> getById(@PathVariable("id") Long id){
        return contrataService.getById(id)
                .map( contrata -> new ResponseEntity<>(contrata, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Contrata>> getAll(){
        return new ResponseEntity<>(contrataService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contrata> create(@Validated @RequestBody ContrataRequestDTO contrataRequestDTO){
        Contrata contrata = contrataMapper.contrataRequestDTOtoContrata(contrataRequestDTO);
        Contrata contratacionCreada = contrataService.create(contrata);
        if (contratacionCreada == null){
            System.out.println("Imprimir error");
        }
        return new ResponseEntity<>(contratacionCreada, HttpStatus.CREATED);
    }
}
