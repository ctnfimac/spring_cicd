package com.cperalta.jardineria.usuario.domain.ports.output;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.records.GardenerRecord;

public interface RegisterGardenerRepositoryPort {
    Gardener register(GardenerRecord gardenerRecord);
    Boolean activate(String email, String token);
}
