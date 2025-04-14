package com.microservice.users.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telefono", unique = true)
    private String telephone;

    @Column(name = "direccion")
    private String address;

    @Column(name = "latitud")
    private String latitude;

    @Column(name = "longitud")
    private String longitude;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private BaseUserEntity baseUser;
}
