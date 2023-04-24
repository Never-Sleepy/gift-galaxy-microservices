package com.giftgalaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("api/v1")
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @GetMapping("ping")
    public Map<String, String> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "pong from user service!");
        return response;
    }
}
