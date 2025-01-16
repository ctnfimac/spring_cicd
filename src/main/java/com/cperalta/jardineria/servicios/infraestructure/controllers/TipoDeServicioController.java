package com.cperalta.jardineria.servicios.infraestructure.controllers;

import com.cperalta.jardineria.servicios.application.services.TipoDeServicioService;
import com.cperalta.jardineria.servicios.domain.models.TipoDeServicio;
import com.cperalta.jardineria.servicios.infraestructure.dto.TipoDeServicioDTO;
import com.cperalta.jardineria.servicios.infraestructure.mapper.TipoDeServicioMapper;
import com.cperalta.jardineria.usuario.domain.models.Estado;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tipodeservicio")
@AllArgsConstructor
public class TipoDeServicioController {
    private final TipoDeServicioService tipoDeServicioService;
    private final TipoDeServicioMapper tipoDeServicioMapper;

    @GetMapping
    public ResponseEntity<List<TipoDeServicioDTO>> getAllTiposDeServicio(){
        List<TipoDeServicioDTO> tiposDeServicios = tipoDeServicioService.getAllTiposDeServicios().stream()
                .map(tipoDeServicioMapper::tipoDeServicioToTipoDeServicioDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tiposDeServicios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDeServicioDTO> getTipoDeServicioById(@PathVariable("id") Long id){
        return tipoDeServicioService.getTipoDeServicioById(id)
                .map(tipoDeServicio -> ResponseEntity.ok(tipoDeServicioMapper.tipoDeServicioToTipoDeServicioDTO(tipoDeServicio)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
