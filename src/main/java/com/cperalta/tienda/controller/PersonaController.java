package com.cperalta.tienda.controller;

import com.cperalta.tienda.dto.EstadoDTO;
import com.cperalta.tienda.dto.PersonaDTO;
import com.cperalta.tienda.dto.PersonaUpdateDTO;
import com.cperalta.tienda.entity.Estado;
import com.cperalta.tienda.entity.Persona;
import com.cperalta.tienda.service.EstadoService;
import com.cperalta.tienda.service.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
@AllArgsConstructor
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAll(){
        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(personaService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Persona> create(@Validated @RequestBody PersonaDTO personaDTO){
        Persona persona = personaService.create(personaDTO);
        return (persona!= null) ?
                new ResponseEntity<>(persona, HttpStatus.CREATED):
                new ResponseEntity<>(persona, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return (personaService.delete(id)) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @PutMapping("{id}")
    public ResponseEntity<Persona> update(@PathVariable("id") Long id,@RequestBody PersonaUpdateDTO personaDTO){
        Persona persona = personaService.update(id, personaDTO);
        return (persona!= null) ?
                new ResponseEntity<>(persona, HttpStatus.OK):
                new ResponseEntity<>(persona, HttpStatus.NOT_FOUND);
    }

}
