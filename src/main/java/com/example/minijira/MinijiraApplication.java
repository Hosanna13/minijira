package com.example.minijira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MinijiraApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinijiraApplication.class, args);
    }

}
