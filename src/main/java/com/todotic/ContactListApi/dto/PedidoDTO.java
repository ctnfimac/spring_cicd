package com.todotic.ContactListApi.dto;

import com.todotic.ContactListApi.entity.Pedido;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoDTO {
    private Integer id;
    private String producto;
    private Integer precio_total;
    private String estado;
    private Integer contact_id;

    public PedidoDTO(Pedido pedido){
        this.id = pedido.getId();
        this.producto = pedido.getProducto();
        this.precio_total = pedido.getPrecio_total();
        this.estado = pedido.getEstado();
        this.contact_id = pedido.getContact().getId();
    }
}
