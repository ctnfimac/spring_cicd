package com.todotic.ContactListApi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="user_role")
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Column(name="granted_date", nullable = false, columnDefinition = "DATE")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name="fk_usuario", nullable = false, insertable = false, updatable = false)
    private Usuario usuario;
}
