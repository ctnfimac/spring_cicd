package com.microservice.users.domain.ports.output;

import com.microservice.users.domain.models.Gardener;
import com.microservice.users.domain.records.GardenerRecord;

public interface RegisterGardenerRepositoryPort {
    Gardener register(GardenerRecord gardenerRecord);
    Boolean activate(String email, String token);
}
