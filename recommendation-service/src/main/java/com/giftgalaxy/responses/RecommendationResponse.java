package com.giftgalaxy.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giftgalaxy.models.Recommendation;

import java.time.LocalDateTime;
import java.util.Map;

public class RecommendationResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double cost;
    private String link;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @JsonProperty("user")
    private UserResponse user;

    public RecommendationResponse(Recommendation recommendation, String user) {
        this.id = recommendation.getId();
        this.title = recommendation.getTitle();
        this.description = recommendation.getDescription();
        this.category = recommendation.getCategory();
        this.cost = recommendation.getCost();
        this.link = recommendation.getLink();
        this.createdAt = recommendation.getCreatedAt();
        this.updatedAt = recommendation.getUpdatedAt();
        this.deletedAt = recommendation.getDeletedAt();

        ObjectMapper objectMapper = new ObjectMapper();
        UserResponse ur = new UserResponse();

        try {
            // Parse JSON string to Java object
            Map<String, Object> jsonMap = objectMapper.readValue(user, Map.class);

            // Read elements from Java object
            int id = (int) jsonMap.get("id");
            String username = (String) jsonMap.get("username");
            String type = (String) jsonMap.get("type");

            ur.setId(id);
            ur.setUsername(username);
            ur.setType(type);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
        this.user = ur;
        System.out.println(this.user.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
