package com.example.baked;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
public class BakedApplication {

  public static void main(String[] args) {
    SpringApplication.run(BakedApplication.class, args);
  }
}
