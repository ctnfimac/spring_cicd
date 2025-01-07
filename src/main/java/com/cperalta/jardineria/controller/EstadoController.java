package com.cperalta.tienda.controller;

import com.cperalta.tienda.dto.EstadoDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estado")
@AllArgsConstructor
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> getAll(){
        return estadoService.getAll();
    }

    @GetMapping("/{id}")
    public Estado getById(@PathVariable("id") Long id){
        return estadoService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Estado> create(@Validated  @RequestBody EstadoDTO estadoDTO){
        Estado estado = estadoService.create(estadoDTO);
        return (estado!= null) ?
                new ResponseEntity<>(estado, HttpStatus.CREATED):
                new ResponseEntity<>(estado, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return (estadoService.delete(id)) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @PutMapping("{id}")
    public ResponseEntity<Estado> update(@PathVariable("id") Long id, @Validated @RequestBody EstadoDTO estadoDTO){
        Estado estado = estadoService.update(id, estadoDTO);
        return (estado!= null) ?
                new ResponseEntity<>(estado, HttpStatus.OK):
                new ResponseEntity<>(estado, HttpStatus.NOT_FOUND);
    }

}
