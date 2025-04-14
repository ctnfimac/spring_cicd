package com.microservice.users.domain.ports.input.gardener;

import com.microservice.users.domain.models.Gardener;
import com.microservice.users.domain.records.GardenerRecord;

public interface RegisterGardenerUseCase {
   Gardener register(GardenerRecord jardineroRecord);
   Boolean activate(String email, String token);
}
