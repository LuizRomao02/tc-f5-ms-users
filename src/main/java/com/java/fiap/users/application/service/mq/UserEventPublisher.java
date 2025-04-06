package com.java.fiap.users.application.service.mq;

import com.java.fiap.users.application.dto.UserRegisteredEvent;
import com.java.fiap.users.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventPublisher {

  private final RabbitTemplate rabbitTemplate;

  public void sendUserRegisterEvent(UserRegisteredEvent event) {
    rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, event);
  }
}
