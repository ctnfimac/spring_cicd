package com.microservice.users.domain.records;

public record GardenerRecord(
        String telephone,
        String name,
        String lastName,
        String email,
        String password,
        String tokenActivation
) {
}
