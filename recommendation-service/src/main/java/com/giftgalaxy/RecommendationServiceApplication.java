package com.giftgalaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("api/v1")
public class RecommendationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecommendationServiceApplication.class, args);
    }
}
