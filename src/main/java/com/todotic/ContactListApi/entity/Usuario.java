package com.todotic.ContactListApi.entity;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @NonNull
    @Id
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
}
