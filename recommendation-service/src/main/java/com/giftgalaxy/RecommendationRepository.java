package com.giftgalaxy;

import org.springframework.data.jpa.repository.JpaRepository;
public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}
