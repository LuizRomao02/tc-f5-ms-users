package com.java.fiap.users.application.service.specification;

import com.java.fiap.users.application.dto.enums.FieldsEnum;
import com.java.fiap.users.application.dto.filter.PatientFilter;
import com.java.fiap.users.domain.model.Patient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PatientSpecification extends BaseSpecification<Patient, PatientFilter> {

  @Override
  public Specification<Patient> getFilter(PatientFilter filter) {
    return ((root, query, criteriaBuilder) -> {
      Specification<Patient> specification =
          Specification.where(attributeIn(FieldsEnum.id.name(), filter.getIds()))
              .and(attributeEquals(FieldsEnum.name.name(), filter.getName()))
              .and(attributeEquals(FieldsEnum.cpf.name(), filter.getCpf()))
              .and(attributeEquals(FieldsEnum.birthDate.name(), filter.getBirthDate()))
              .and(attributeEquals(FieldsEnum.phone.name(), filter.getPhone()))
              .and(
                  attributeBetweenDates(
                      FieldsEnum.createdAt.name(),
                      filter.getCreatedAtStart(),
                      filter.getCreatedAtEnd()));

      return specification.toPredicate(root, query, criteriaBuilder);
    });
  }
}
