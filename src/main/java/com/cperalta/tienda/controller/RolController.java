package com.cperalta.tienda.controller;

import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rol")
@AllArgsConstructor
public class RolController {
    @Autowired
    private final RolService rolService;

    @GetMapping("")
    public List<Rol> getAll(){
        return rolService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
            return new ResponseEntity<>(rolService.getById(id), HttpStatus.OK);
    }
}
