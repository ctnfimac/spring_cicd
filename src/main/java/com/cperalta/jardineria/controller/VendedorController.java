package com.cperalta.jardineria.controller;

import com.cperalta.jardineria.dto.VendedorDTO;
import com.cperalta.jardineria.dto.VendedorResponseDTO;
import com.cperalta.jardineria.dto.VendedorUpdateDTO;
import com.cperalta.jardineria.entity.Vendedor;
import com.cperalta.jardineria.service.VendedorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedor")
@AllArgsConstructor
public class VendedorController {

    @Autowired
    private final VendedorService vendedorService;

    @GetMapping
    public List<VendedorResponseDTO> getAll(){
        return vendedorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorResponseDTO> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(vendedorService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vendedor> create(@RequestBody VendedorDTO vendedorDTO){
        return new ResponseEntity<>(vendedorService.create(vendedorDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return (vendedorService.delete(id)) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Vendedor> update(@PathVariable("id") Long id, @RequestBody VendedorUpdateDTO vendedorUpdateDTO){
        Vendedor vendedor = vendedorService.update(id, vendedorUpdateDTO);
        return (vendedor!= null) ?
                new ResponseEntity<>(vendedor, HttpStatus.OK):
                new ResponseEntity<>(vendedor, HttpStatus.NOT_FOUND);
    }
}
