package com.java.fiap.users.config.security;

import com.java.fiap.users.application.dto.TokenValidationRequest;
import com.java.fiap.users.config.feign.TokenValidationClient;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Configuration
@RequiredArgsConstructor
public class TokenRevocationConfig {

  private final TokenValidationClient validationClient;

  @Bean
  public Function<Message<TokenValidationRequest>, Message<Boolean>> validateTokenRevocation() {
    return message -> {
      TokenValidationRequest request = message.getPayload();
      boolean isValid = validationClient.validate(request);
      return MessageBuilder.withPayload(isValid).build();
    };
  }
}
