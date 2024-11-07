package com.todotic.ContactListApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @NonNull
    @Column(length = 50, unique = true)
    private  String producto;

    @NonNull
    @Column(length = 30)
    private String estado; // PENDIENTE DE PAGO, PAGADO, PENDIENTE DE ENTREGA, FINALIZADO

    private Integer precio_total = 0;

    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "fk_contact", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Contact contact;
}
