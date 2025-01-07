package com.cperalta.jardineria.usuario.infraestructure.controllers;

import com.cperalta.jardineria.usuario.application.services.EstadoService;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import com.cperalta.jardineria.usuario.infraestructure.dto.EstadoRequestDTO;
import com.cperalta.jardineria.usuario.infraestructure.mapper.EstadoMapper;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/estado")
@AllArgsConstructor
public class EstadoV2Controller {
    //@Autowired
    private final EstadoService estadoService;
    private final EstadoMapper estadoMapper;

    @GetMapping
    public ResponseEntity<List<Estado>> getAllEstados(){
        List<Estado> estados = estadoService.getAllEstados();
        return new ResponseEntity<>(estados, HttpStatus.OK);
    }

    @GetMapping("/{idEstado}")
    public ResponseEntity<Estado> getEstado(@PathVariable("idEstado") Long idEstado){
        return estadoService.getEstadoById(idEstado)
                .map( estado -> new ResponseEntity<>(estado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Estado> createEstado(@Validated @RequestBody EstadoRequestDTO estado){
        Estado estadoNuevo = estadoMapper.estadoRequestDTOtoEstado(estado);
        Estado createdEstado = estadoService.createEstado(estadoNuevo);
        return new ResponseEntity<>(createdEstado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEstado}")
    public ResponseEntity<Void> deleteEstado(@PathVariable("idEstado") Long idEstado){
        return estadoService.deleteEstado(idEstado) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{idEstado}")
    public ResponseEntity<Estado> updateEstado(@PathVariable("idEstado") Long idEstado,
                                               @Validated @RequestBody EstadoRequestDTO estadoRequestDTO){
        Estado estado = estadoMapper.estadoRequestDTOtoEstado(estadoRequestDTO);
        return estadoService.updateEstado(idEstado, estado)
                .map( estadoActualizado -> new ResponseEntity<>(estadoActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
