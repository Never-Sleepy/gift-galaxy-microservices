package com.giftgalaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/ping")
public class RecommendationServiceApplication {
    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceApplication(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(RecommendationServiceApplication.class, args);
    }

/*    @GetMapping()
    public Map<String, String> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "pong from recommendation service!");
        return response;
    }*/
    @GetMapping
    public List<Recommendation> getRecommendations() {
        return recommendationRepository.findAll();
    }

    record NewRecommendation(String title, String description, String category, String cost, String link) {
    }

    @PostMapping
    public void addRecommendation(@RequestBody NewRecommendation request) {
        Recommendation recommendation = new Recommendation();
        recommendation.setTitle(request.title());
        recommendation.setDescription(request.description());
        recommendation.setCategory(request.category());
        recommendation.setCost(request.cost());
        recommendation.setLink(request.link());
        recommendation.setCreation();
        recommendationRepository.save(recommendation);
    }

    @PutMapping("{recommendationId}")
    public void updateRecommendation(@PathVariable("recommendationId") Integer id, @RequestBody NewRecommendation request) {
        Recommendation recommendation = recommendationRepository.findById(id).get();
        recommendation.setTitle(request.title());
        recommendation.setDescription(request.description());
        recommendation.setCategory(request.category());
        recommendation.setCost(request.cost());
        recommendation.setLink(request.link());
        recommendation.setUpdate();
        recommendationRepository.save(recommendation);
    }

    @DeleteMapping("{recommendationId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        Recommendation recommendation = recommendationRepository.findById(id).get();
        recommendation.setDelete();
    }

}
