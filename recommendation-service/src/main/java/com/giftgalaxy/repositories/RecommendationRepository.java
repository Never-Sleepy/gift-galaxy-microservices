package com.giftgalaxy.repositories;

import com.giftgalaxy.models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}