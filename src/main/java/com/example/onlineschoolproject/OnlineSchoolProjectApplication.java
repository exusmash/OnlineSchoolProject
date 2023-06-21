package com.example.onlineschoolproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineSchoolProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSchoolProjectApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Swagger path: http://localhost:8080/swagger-ui/index.html");
    }
}
