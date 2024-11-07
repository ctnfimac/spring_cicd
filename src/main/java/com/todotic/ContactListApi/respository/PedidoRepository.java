package com.todotic.ContactListApi.respository;

import com.todotic.ContactListApi.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
}
