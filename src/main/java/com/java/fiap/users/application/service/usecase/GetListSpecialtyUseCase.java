package com.java.fiap.users.application.service.usecase;

import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.domain.model.Specialty;
import java.util.Set;

public interface GetListSpecialtyUseCase extends UseCase<SpecialtyFilter, Set<Specialty>> {

  @Override
  Set<Specialty> execute(SpecialtyFilter filter);
}
