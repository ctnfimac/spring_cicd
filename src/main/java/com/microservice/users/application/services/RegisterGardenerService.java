package com.microservice.users.application.services;

import com.microservice.users.domain.models.Gardener;
import com.microservice.users.domain.ports.input.gardener.RegisterGardenerUseCase;
import com.microservice.users.domain.records.GardenerRecord;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterGardenerService implements RegisterGardenerUseCase {

    private final RegisterGardenerUseCase registerGardenerUseCase;

    @Override
    public Gardener register(GardenerRecord gardenerRecord) {
        return registerGardenerUseCase.register(gardenerRecord);
    }

    @Override
    public Boolean activate(String email, String token) {
        return registerGardenerUseCase.activate(email, token);
    }

}
