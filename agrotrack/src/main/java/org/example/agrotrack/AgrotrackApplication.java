package org.example.agrotrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AgrotrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgrotrackApplication.class, args);
    }

}
