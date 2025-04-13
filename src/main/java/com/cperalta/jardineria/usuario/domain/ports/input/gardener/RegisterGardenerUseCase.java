package com.cperalta.jardineria.usuario.domain.ports.input.gardener;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.records.GardenerRecord;

public interface RegisterGardenerUseCase {
   Gardener register(GardenerRecord jardineroRecord);
   Boolean activate(String email, String token);
}
