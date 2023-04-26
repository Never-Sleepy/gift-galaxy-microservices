package com.giftgalaxy.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class PingController {

    @GetMapping("ping")
    public Map<String, String> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "pong from user service!");
        return response;
    }
}