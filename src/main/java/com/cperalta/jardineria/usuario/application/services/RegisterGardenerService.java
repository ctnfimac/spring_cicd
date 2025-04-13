package com.cperalta.jardineria.usuario.application.services;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.ports.input.gardener.RegisterGardenerUseCase;
import com.cperalta.jardineria.usuario.domain.records.GardenerRecord;
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
