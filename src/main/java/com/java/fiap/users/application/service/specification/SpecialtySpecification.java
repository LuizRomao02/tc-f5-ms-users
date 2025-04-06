package com.java.fiap.users.application.service.specification;

import com.java.fiap.users.application.dto.enums.FieldsEnum;
import com.java.fiap.users.application.dto.filter.SpecialtyFilter;
import com.java.fiap.users.domain.model.Specialty;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecialtySpecification extends BaseSpecification<Specialty, SpecialtyFilter> {

  @Override
  public Specification<Specialty> getFilter(SpecialtyFilter filter) {
    return ((root, query, criteriaBuilder) -> {
      Specification<Specialty> specification =
          Specification.where(attributeIn(FieldsEnum.id.name(), filter.getIds()))
              .and(attributeEquals(FieldsEnum.name.name(), filter.getName()))
              .and(
                  attributeBetweenDates(
                      FieldsEnum.createdAt.name(),
                      filter.getCreatedAtStart(),
                      filter.getCreatedAtEnd()))
              .and(hasDoctorWithId(filter.getDoctorsIds()))
              .and(hasDoctorWithName(filter.getDoctorName()));

      return specification.toPredicate(root, query, criteriaBuilder);
    });
  }
}
