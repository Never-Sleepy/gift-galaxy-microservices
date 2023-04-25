package com.giftgalaxy.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giftgalaxy.models.Recommendation;
import com.giftgalaxy.repositories.RecommendationRepository;
import com.giftgalaxy.responses.RecommendationResponse;
import com.giftgalaxy.responses.UserResponse;
import com.giftgalaxy.specifications.RecommendationSpecifications;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class RecommendationController {

    @Value("${USER_SERVICE}")
    private String userServiceUrl;
    private final RecommendationRepository recommendationRepository;

    public RecommendationController(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @GetMapping("recommendations")
    public List<Recommendation> getRecommendations() {
        return recommendationRepository.findAll(RecommendationSpecifications.isNotDeleted());
    }

    @GetMapping("recommendation/{id}")
    public RecommendationResponse get(@PathVariable("id") Long id) {
        String id_value = String.valueOf(id);
        String url = userServiceUrl + "/user/" + id_value;

        ClientHttpConnector connector = new ReactorClientHttpConnector();

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder()))
                .build();

        WebClient webClient = WebClient.builder()
                .clientConnector(connector)
                .exchangeStrategies(exchangeStrategies)
                .build();

        String jsonResponse = webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(String.class))
                .block();

        System.out.println(jsonResponse);

        Recommendation recommendation = recommendationRepository
                .findOne(RecommendationSpecifications.isNotDeleted()
                        .and(RecommendationSpecifications.getById(id)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recommendation not found"));

        return new RecommendationResponse(
                recommendation, jsonResponse
        );
    }

    record NewRecommendation(
            String title,
            String description,
            String category,
            Double cost,
            @JsonProperty("user_id") Long userId,
            String link
    ) {}

    @PostMapping("recommendation")
    @ResponseBody
    public Recommendation create(@RequestBody NewRecommendation request) {
        Recommendation recommendation = new Recommendation();
        recommendation.setTitle(request.title());
        recommendation.setDescription(request.description());
        recommendation.setCategory(request.category());
        recommendation.setCost(request.cost);
        recommendation.setUserId(request.userId);
        recommendation.setLink(request.link());

        // setup createdAt and updatedAt using LocalDateTime
        LocalDateTime date = LocalDateTime.now();
        recommendation.setCreatedAt(date);
        recommendation.setUpdatedAt(date);

        recommendationRepository.save(recommendation);

        return recommendation;
    }

    @PutMapping("recommendation/{id}")
    public Recommendation update(@PathVariable("id") Long id, @RequestBody NewRecommendation request) {
        Recommendation recommendation = recommendationRepository
                .findOne(RecommendationSpecifications.isNotDeleted()
                        .and(RecommendationSpecifications.getById(id)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recommendation not found"));
        recommendation.setTitle(request.title());
        recommendation.setDescription(request.description());
        recommendation.setCategory(request.category());
        recommendation.setCost(request.cost);
        recommendation.setLink(request.link());

        // setup createdAt and updatedAt using LocalDateTime
        LocalDateTime date = LocalDateTime.now();
        recommendation.setUpdatedAt(date);

        recommendationRepository.save(recommendation);

        return recommendation;
    }

    @DeleteMapping("recommendation/{id}")
    public Recommendation delete(@PathVariable("id") Long id) {
        Recommendation recommendation = recommendationRepository
                .findOne(RecommendationSpecifications.isNotDeleted()
                        .and(RecommendationSpecifications.getById(id)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recommendation not found"));

        // setup createdAt and updatedAt using LocalDateTime
        LocalDateTime date = LocalDateTime.now();
        recommendation.setDeletedAt(date);

        recommendationRepository.save(recommendation);

        return recommendation;
    }
}
