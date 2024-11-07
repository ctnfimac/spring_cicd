package com.todotic.ContactListApi.service;

import com.todotic.ContactListApi.entity.Pedido;
import com.todotic.ContactListApi.respository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public List<Pedido> findAll(){
        return (List<Pedido>) pedidoRepository.findAll();
    }
}
