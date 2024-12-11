package com.cperalta.tienda.controller;

import com.cperalta.tienda.dto.VendedorResponseDTO;
import com.cperalta.tienda.service.VendedorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
