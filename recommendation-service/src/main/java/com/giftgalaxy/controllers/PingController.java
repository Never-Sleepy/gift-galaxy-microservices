package com.giftgalaxy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("ping")
public class PingController {
    @RequestMapping
    public Map<String, String> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "pong from recommendation service!");
        return response;
    }
}