package com.cperalta.tienda.controller;

import com.cperalta.tienda.dto.RolDTO;
import com.cperalta.tienda.entity.Rol;
import com.cperalta.tienda.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Rol> save(@Validated @RequestBody RolDTO rolDTO){
        return new ResponseEntity<>(rolService.create(rolDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(rolService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable("id") Long id, @RequestBody RolDTO rolDTO){
        Rol rolActualizado = rolService.update(id, rolDTO);
        if(rolActualizado != null){
            return new ResponseEntity<>(rolActualizado,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(rolActualizado,HttpStatus.NOT_FOUND);
        }

    }
}
