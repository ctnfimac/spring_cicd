package com.todotic.ContactListApi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private LocalDateTime createdAt;

    private String pathUrl;
}
