package com.cperalta.tienda.controller;

import com.cperalta.tienda.dto.*;
import com.cperalta.tienda.entity.Comprador;
import com.cperalta.tienda.entity.Vendedor;
import com.cperalta.tienda.service.CompradorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprador")
@AllArgsConstructor
public class CompradorController {

    @Autowired
    private final CompradorService compradorService;

    @GetMapping
    public List<CompradorResponseDTO> getAll(){
        return compradorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompradorResponseDTO> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(compradorService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comprador> create(@RequestBody CompradorDTO compradorDTO){
        return new ResponseEntity<>(compradorService.create(compradorDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Comprador> update(@PathVariable("id") Long id, @RequestBody CompradorUpdateDTO compradorUpdateDTO){
        Comprador comprador = compradorService.update(id, compradorUpdateDTO);
        return (comprador != null) ?
                new ResponseEntity<>(comprador, HttpStatus.OK):
                new ResponseEntity<>(comprador, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return (compradorService.delete(id)) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
