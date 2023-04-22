package com.giftgalaxy.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "recommendations")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "link")
    private String link;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Recommendation(Long id, String title, String description, String category, double cost, Long userId, String link, LocalDateTime createdAt, LocalDateTime updateAt, LocalDateTime deletedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.cost = cost;
        this.userId = userId;
        this.link = link;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deletedAt = deletedAt;
    }

    public Recommendation() {

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recommendation that = (Recommendation) o;
        return Double.compare(that.cost, cost) == 0 && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(category, that.category) && Objects.equals(userId, that.userId) && Objects.equals(link, that.link) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updateAt, that.updateAt) && Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, cost, userId, link, createdAt, updateAt, deletedAt);
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", cost=" + cost +
                ", userId=" + userId +
                ", link='" + link + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}