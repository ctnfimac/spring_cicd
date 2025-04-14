package com.microservice.users.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class Status {
    private Long id;
    private String description;
}
