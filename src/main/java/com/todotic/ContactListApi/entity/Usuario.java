package com.todotic.ContactListApi.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    @Column(nullable = false, length = 20)
    private String username;

    @NonNull
    @Column(nullable = false, length = 200)
    private String password;

    @NonNull
    @Column(unique = true, length = 50)
    private String email;

    @Column(nullable = false, columnDefinition = "INTEGER")
    private Boolean locked;

    @Column(nullable = false, columnDefinition = "INTEGER")
    private Boolean disabled;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER) // fetch: forma de recuperacion de los roles, para que se consulten automaticamente
    private List<UserRoleEntity> roles;
}
