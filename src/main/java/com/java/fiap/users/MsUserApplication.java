package com.java.fiap.users;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MsUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsUserApplication.class, args);
  }
}
