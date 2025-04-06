package com.java.fiap.users.application.service.specification;

import jakarta.persistence.criteria.Join;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T, P> {

  public abstract Specification<T> getFilter(P filter);

  protected <R> Specification<T> attributeEquals(Join<?, ?> join, String attribute, R value) {
    return (root, query, cb) -> {
      if (value == null) {
        return null;
      }
      return cb.equal(join.get(attribute).as(value.getClass()), value);
    };
  }

  protected <R> Specification<T> attributeEquals(String attribute, R value) {
    return (root, query, cb) -> {
      if (value == null) {
        return null;
      }
      return cb.equal(root.get(attribute).as(value.getClass()), value);
    };
  }

  protected Specification<T> attributeBetweenDates(
      Join<?, ?> join, String attribute, LocalDateTime startDate, LocalDateTime endDate) {
    return (root, query, cb) -> {
      if (startDate == null || endDate == null) {
        return null;
      }
      return cb.between(join.get(attribute), startDate, endDate);
    };
  }

  protected Specification<T> attributeBetweenDates(
      String attribute, LocalDateTime startDate, LocalDateTime endDate) {
    return (root, query, cb) -> {
      if (startDate == null || endDate == null) {
        return null;
      }
      return cb.between(root.get(attribute), startDate, endDate);
    };
  }

  protected Specification<T> attributeContains(Join<?, ?> join, String attribute, String value) {
    return (root, query, cb) -> {
      if (value == null) {
        return null;
      }
      return cb.like(cb.lower(join.get(attribute)), containsLowerCase(value));
    };
  }

  protected Specification<T> attributeContains(String attribute, String value) {
    return (root, query, cb) -> {
      if (value == null) {
        return null;
      }
      return cb.like(root.get(attribute), containsLowerCase(value));
    };
  }

  protected Specification<T> attributeIn(Join<?, ?> join, String attribute, List<?> values) {
    return (root, query, cb) -> {
      if (values == null || values.isEmpty()) {
        return null;
      }
      return join.get(attribute).in(values);
    };
  }

  protected Specification<T> attributeIn(String attribute, List<?> values) {
    return (root, query, cb) -> {
      if (values == null || values.isEmpty()) {
        return null;
      }
      return root.get(attribute).in(values);
    };
  }

  protected Specification<T> attributeNotIn(String attribute, List<?> values) {
    return (root, query, cb) -> {
      if (values == null || values.isEmpty()) {
        return null;
      }
      return root.get(attribute).in(values).not();
    };
  }

  protected Specification<T> attributeNotIn(Join<?, ?> join, String attribute, List<?> values) {
    return (root, query, cb) -> {
      if (values == null || values.isEmpty()) {
        return null;
      }
      return join.get(attribute).in(values).not();
    };
  }

  protected String containsLowerCase(String searchField) {
    return "%" + searchField.toLowerCase() + "%";
  }
}
