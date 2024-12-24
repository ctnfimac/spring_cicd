package com.cperalta.tienda.controller;

import com.cperalta.tienda.dto.EstadoCompraDTO;
import com.cperalta.tienda.dto.EstadoDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.EstadoCompra;
import com.cperalta.tienda.service.EstadoCompraService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadocompra")
@AllArgsConstructor
public class EstadoCompraController {
    @Autowired
    private EstadoCompraService estadoCompraService;

    @GetMapping()
    public List<EstadoCompra> getAll(){
        return estadoCompraService.getAll();
    }

    @GetMapping("/{id}")
    public EstadoCompraDTO getById(@PathVariable("id") Long id){
        return estadoCompraService.getById(id);
    }

    @PostMapping
    public ResponseEntity<EstadoCompraDTO> create(@Validated @RequestBody EstadoCompraDTO estadoCompraDTO){
        EstadoCompraDTO estadoCompra = estadoCompraService.create(estadoCompraDTO);
        return (estadoCompra!= null) ?
                new ResponseEntity<>(estadoCompra, HttpStatus.CREATED):
                new ResponseEntity<>(estadoCompra, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return (estadoCompraService.delete(id)) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<EstadoCompraDTO> update(@PathVariable("id") Long id,
                                         @Validated @RequestBody EstadoCompraDTO estadoCompraDTO){
        EstadoCompraDTO estado = estadoCompraService.update(id, estadoCompraDTO);
        return (estado!= null) ?
                new ResponseEntity<>(estado, HttpStatus.OK):
                new ResponseEntity<>(estado, HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/buscar/{descripcion}")
    public ResponseEntity<EstadoCompra> getEstadoCompraByDescripcion(@RequestParam("descripcion") String descripcion) {
        EstadoCompra estadoCompra = estadoCompraService.getByDescripcion(descripcion);
        return ResponseEntity.ok(estadoCompra);
    }*/

    @GetMapping("/buscar/{descripcion}")
    public ResponseEntity<EstadoCompra> getEstadoCompraByDescripcion(@PathVariable String descripcion) {
        EstadoCompra estadoCompra = estadoCompraService.getByDescripcion(descripcion);
        return ResponseEntity.ok(estadoCompra);
    }
}
