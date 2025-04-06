package com.java.fiap.users.common;

import com.java.fiap.users.application.dto.UserRegisteredEvent;
import com.java.fiap.users.application.dto.enums.UserTypeEnum;
import com.java.fiap.users.application.service.mq.UserEventPublisher;
import com.java.fiap.users.domain.model.Doctor;
import com.java.fiap.users.domain.model.Patient;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class NotifyUserRegisterAspect {

  private final UserEventPublisher userEventPublisher;

  @AfterReturning(
      pointcut = "@annotation(com.java.fiap.users.common.NotifyUserRegister)",
      returning = "result")
  public void afterUserSaved(Object result) {
    if (result instanceof Doctor savedDoctor) {
      userEventPublisher.sendUserRegisterEvent(
          UserRegisteredEvent.builder()
              .name(savedDoctor.getName())
              .id(savedDoctor.getId())
              .email(savedDoctor.getEmail())
              .type(UserTypeEnum.DOCTOR)
              .build());
    }
    if (result instanceof Patient savedPatient) {
      userEventPublisher.sendUserRegisterEvent(
          UserRegisteredEvent.builder()
              .name(savedPatient.getName())
              .id(savedPatient.getId())
              .email(savedPatient.getEmail())
              .type(UserTypeEnum.PATIENT)
              .build());
    }
  }
}
