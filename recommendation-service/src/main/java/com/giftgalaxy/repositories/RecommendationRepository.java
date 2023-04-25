package com.giftgalaxy.repositories;

import com.giftgalaxy.models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer>, JpaSpecificationExecutor<Recommendation> {}
