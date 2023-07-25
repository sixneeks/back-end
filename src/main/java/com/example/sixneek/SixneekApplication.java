package com.example.sixneek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SixneekApplication {

    public static void main(String[] args) {
        SpringApplication.run(SixneekApplication.class, args);
    }

}
