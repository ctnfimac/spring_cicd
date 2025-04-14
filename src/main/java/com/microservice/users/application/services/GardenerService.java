package com.microservice.users.application.services;

import com.microservice.users.domain.models.Gardener;
import com.microservice.users.domain.ports.input.gardener.CreateGardenerUseCase;
import com.microservice.users.domain.ports.input.gardener.DeleteGardenerUseCase;
import com.microservice.users.domain.ports.input.gardener.RetrieveGardenerUseCase;
import com.microservice.users.domain.ports.input.gardener.UpdateGardenerUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GardenerService implements RetrieveGardenerUseCase, CreateGardenerUseCase,
        UpdateGardenerUseCase, DeleteGardenerUseCase {

    private final RetrieveGardenerUseCase retrieveGardenerUseCase;
    private final CreateGardenerUseCase createGardenerUseCase;
    private final UpdateGardenerUseCase updateGardenerUseCase;
    private final DeleteGardenerUseCase deleteGardenerUseCase;

    @Override
    public Optional<Gardener> getByEmail(String email) {
        return retrieveGardenerUseCase.getByEmail(email);
    }

    @Override
    public Optional<Gardener> getByEmailAndPassword(String email, String contrasenia) {
        return retrieveGardenerUseCase.getByEmailAndPassword(email ,contrasenia);
    }

    @Override
    public Optional<Gardener> getById(Long id) {
        return retrieveGardenerUseCase.getById(id);
    }

    @Override
    public List<Gardener> getAll() {
        return retrieveGardenerUseCase.getAll();
    }

    @Override
    public Gardener create(Gardener gardener) {
        return createGardenerUseCase.create(gardener);
    }

    @Override
    public boolean delete(Long id) {
        return deleteGardenerUseCase.delete(id);
    }

    @Override
    public Gardener update(Long id, Gardener gardener) {
        return updateGardenerUseCase.update(id, gardener);
    }
}
