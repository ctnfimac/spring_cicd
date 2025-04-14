package com.microservice.users.application.usecases.gardener;

import com.microservice.users.domain.models.Gardener;
import com.microservice.users.domain.ports.input.gardener.RetrieveGardenerUseCase;
import com.microservice.users.domain.ports.output.GardenerRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrieveGardenerUseCaseImpl implements RetrieveGardenerUseCase {

    private GardenerRepositoryPort gardenerRepositoryPort;

    @Override
    public Optional<Gardener> getByEmail(String email) {
        return gardenerRepositoryPort.findByEmail(email);
    }

    @Override
    public Optional<Gardener> getByEmailAndPassword(String email, String contrasenia) {
        return gardenerRepositoryPort.getByEmailAndPassword(email, contrasenia);
    }

    @Override
    public Optional<Gardener> getById(Long id) {
        return gardenerRepositoryPort.getById(id);
    }

    @Override
    public List<Gardener> getAll() {
        return gardenerRepositoryPort.getAll();
    }
}
