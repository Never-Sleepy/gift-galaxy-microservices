package com.giftgalaxy.controllers;

import com.giftgalaxy.models.Recommendation;
import com.giftgalaxy.repositories.RecommendationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class RecommendationController {
    private final RecommendationRepository recommendationRepository;

    public RecommendationController(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @GetMapping("recommendation")
    public List<Recommendation> getRecommendations() {
        return recommendationRepository.findAll();
    }

    record NewRecommendation(String title, String description, String category, String cost, String link) {
    }

    @PostMapping("recommendation")
    public void addRecommendation(@RequestBody NewRecommendation request) {
        Recommendation recommendation = new Recommendation();
        recommendation.setTitle(request.title());
        recommendation.setDescription(request.description());
        recommendation.setCategory(request.category());
        recommendation.setLink(request.link());
        recommendationRepository.save(recommendation);
    }

    @PutMapping("recommendation/{recommendationId}")
    public void updateRecommendation(@PathVariable("recommendationId") Integer id, @RequestBody NewRecommendation request) {
        Recommendation recommendation = recommendationRepository.findById(id).get();
        recommendation.setTitle(request.title());
        recommendation.setDescription(request.description());
        recommendation.setCategory(request.category());
        recommendation.setLink(request.link());
        recommendationRepository.save(recommendation);
    }

    @DeleteMapping("recommendation/{recommendationId}")
    public void deleteRecommendation(@PathVariable("recommendationId") Integer id) {
        Recommendation recommendation = recommendationRepository.findById(id).get();
    }
}
