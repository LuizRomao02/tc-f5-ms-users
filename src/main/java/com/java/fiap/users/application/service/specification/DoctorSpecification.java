package com.java.fiap.users.application.service.specification;

import com.java.fiap.users.application.dto.enums.FieldsEnum;
import com.java.fiap.users.application.dto.filter.DoctorFilter;
import com.java.fiap.users.domain.model.Doctor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DoctorSpecification extends BaseSpecification<Doctor, DoctorFilter> {

  @Override
  public Specification<Doctor> getFilter(DoctorFilter filter) {
    return ((root, query, criteriaBuilder) -> {
      Specification<Doctor> specification =
          Specification.where(attributeIn(FieldsEnum.id.name(), filter.getIds()))
              .and(attributeEquals(FieldsEnum.name.name(), filter.getName()))
              .and(attributeEquals(FieldsEnum.crm.name(), filter.getCrm()))
              .and(
                  attributeBetweenDates(
                      FieldsEnum.createdAt.name(),
                      filter.getCreatedAtStart(),
                      filter.getCreatedAtEnd()));

      return specification.toPredicate(root, query, criteriaBuilder);
    });
  }
}
