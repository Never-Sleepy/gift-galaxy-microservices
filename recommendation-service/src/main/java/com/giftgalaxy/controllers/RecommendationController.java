package com.giftgalaxy.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.giftgalaxy.models.Recommendation;
import com.giftgalaxy.repositories.RecommendationRepository;
import com.giftgalaxy.specifications.RecommendationSpecifications;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class RecommendationController {
    private final RecommendationRepository recommendationRepository;

    public RecommendationController(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @GetMapping("recommendations")
    public List<Recommendation> getRecommendations() {
        return recommendationRepository.findAll(RecommendationSpecifications.isNotDeleted());
    }

    @GetMapping("recommendation/{id}")
    public Recommendation get(@PathVariable("id") Long id) {
        return recommendationRepository
                .findOne(RecommendationSpecifications.isNotDeleted()
                        .and(RecommendationSpecifications.getById(id)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recommendation not found"));
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
