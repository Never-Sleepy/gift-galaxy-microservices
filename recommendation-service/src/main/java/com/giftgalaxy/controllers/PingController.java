package com.giftgalaxy.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class PingController {

    @GetMapping("ping")
    public Map<String, String> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "pong from recommendation service!");
        return response;
    }
}