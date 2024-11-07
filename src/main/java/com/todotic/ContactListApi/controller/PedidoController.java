package com.todotic.ContactListApi.controller;

import com.todotic.ContactListApi.dto.PedidoDTO;
import com.todotic.ContactListApi.entity.Pedido;
import com.todotic.ContactListApi.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/contacts/pedidos")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping("")
    public ResponseEntity<List<PedidoDTO>> list(){
        List<Pedido> pedidos = pedidoService.findAll();
        List<PedidoDTO> pedidosDTO = pedidos.stream()
                .map(PedidoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidosDTO);
    }
}
