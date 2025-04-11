package com.java.fiap.users.config.feign;

import com.java.fiap.users.application.dto.TokenValidationRequest;
import com.java.fiap.users.config.security.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "tc-f5-ms-login",
    path = "/api/token-validation",
    configuration = FeignConfig.class)
public interface TokenValidationClient {

  @PostMapping("/validate")
  Boolean validate(@RequestBody TokenValidationRequest request);
}
