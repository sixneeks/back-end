package com.example.sixneek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SixneekApplication {

    public static void main(String[] args) {
        SpringApplication.run(SixneekApplication.class, args);
    }

}
