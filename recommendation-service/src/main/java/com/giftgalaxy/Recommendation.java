package com.giftgalaxy;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Recommendation {

    private Integer id;
    private String title;
    private String description;
    private String category;
    private String cost;
    private String link;
    private LocalDateTime creation;
    private LocalDateTime update;
    private LocalDateTime delete;

    public Recommendation(Integer id, String title, String description, String category, String cost, String link, LocalDateTime creation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.cost = cost;
        this.link = link;
        this.creation = creation;
    }

    public Recommendation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation() {
        this.creation = LocalDateTime.now();
    }

    public LocalDateTime getUpdate() {
        return update;
    }

    public void setUpdate() {
        this.update = LocalDateTime.now();
    }

    public LocalDateTime getDelete() {
        return delete;
    }

    public void setDelete() {
        this.delete = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recommendation that = (Recommendation) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(category, that.category) && Objects.equals(cost, that.cost) && Objects.equals(link, that.link) && Objects.equals(creation, that.creation) && Objects.equals(update, that.update) && Objects.equals(delete, that.delete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, cost, link, creation, update, delete);
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", cost='" + cost + '\'' +
                ", link='" + link + '\'' +
                ", creation=" + creation +
                ", update=" + update +
                ", delete=" + delete +
                '}';
    }
}