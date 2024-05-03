package com.suhsaechan.dongbanza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DongbanzaApplication {

  public static void main(String[] args) {
    SpringApplication.run(DongbanzaApplication.class, args);
  }

}
